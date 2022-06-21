package com.portfolio.task;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileCheckTask {

    @Autowired
    ImageMapper imageMapper;

    @Scheduled(cron="0 * * * * *")
    public void checkFiles() {
        System.out.println("1");
        // Calendar 객체 생성
        Calendar cal = Calendar.getInstance();

        // 날짜 담을 변수
        String datePath;

        cal.add(Calendar.DATE, -1);
        System.out.println(2);

        // 날짜
        datePath = String.format("/%04d%02d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(datePath);

        List<ImageVO> oldFiles = imageMapper.selectOldFiles();

        // 현재 클래스 파일 절대경로 구하기
        String currentDir = this.getClass().getResource("").getPath();

        // WEB-INF 디렉토리까지 경로 구하기
        int idx = currentDir.indexOf("classes");

        List<Path>  oldFilePaths = oldFiles.stream().map(imageVO -> Paths.get(currentDir.substring(1, idx), imageVO.getImagePath())).collect(Collectors.toList());

        File targetDir = Paths.get(currentDir.substring(1, idx), File.separator, "uploadedImages" + datePath).toFile();

        // 데이터베이스 상 파일과 실제 파일 비교 후 데이터베이스에 존재하지 않으면 삭제
        File[] FiledToBeRemoved = targetDir.listFiles(file -> !oldFilePaths.contains(file.toPath()));

        for (File file : FiledToBeRemoved) {
            System.out.println(file.toString());
            file.delete();
        }
    }
}