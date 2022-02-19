package com.portfolio.commons.util;

import com.portfolio.domain.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class UploadFileUtils {

    public static List<String> uploadFile(MultipartHttpServletRequest mtfRequest) {

        // multipartFile 리스트
        List<MultipartFile> mtfs = mtfRequest.getFiles("image");

        // 데이터베이스에 기록되는 경로를 담을 리스트
        List<String> fileList = new ArrayList<>();

        for (MultipartFile mtf : mtfs) {

            // 파일 이름
            String originalFileName = mtf.getOriginalFilename();

            // 공백문자를 언더스코어로 교체하기
            originalFileName = originalFileName.replace(' ', '_');

            // 업로드 경로
            String uploadPath = mtfRequest.getSession().getServletContext().getRealPath(File.separator + "WEB-INF" + File.separator + "uploadedImages" + File.separator);

            // 조회시 과부하를 막기 위한 경로 구분용 현재 날짜를 yyyyMMdd 형태로 반환받기
            String date = getTodayDate();

            // 파일 이름 중복문제를 해결하기 위한 UUID
            String uuid = UUID.randomUUID().toString();

            // 데이터베이스에 기록될 경로 형태
            // 현재날짜 + UUID + "_" + 파일
            String dbFile = date + uuid + "_" + originalFileName;

            // 파일 저장 경로
            String saveFile = uploadPath + dbFile;

            // 위에서 생성했던 리스트에 파일 이름 담기
            fileList.add(dbFile);

            try {
                // 파일 경로에 저장하기
                mtf.transferTo(new File(saveFile));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileList;  // 파일 리스트 반환

    }

    public static void deleteFile(HttpServletRequest request, List<FileVO> atchList) {

        String uploadPath = request.getSession().getServletContext().getRealPath(File.separator + "uploadedImages" + File.separator);

        for (FileVO fileVO : atchList) {

            String filePath = uploadPath + fileVO.getPath();

            File file = new File(filePath);

            if (file.exists()) {
                boolean res = file.delete();

                if (res) {
                    System.out.println("파일 삭제 성공");
                } else {
                    System.out.println("파일 삭제 실");
                }
            }
        }

    }

    private static String getTodayDate() {

        // Calendar 객체 생성
        Calendar cal = Calendar.getInstance();

        // 날짜 담을 변수
        String datePath;

        // 날짜 + 세퍼레이터 스트링
        datePath = String.format("%04d%02d%02d%s", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), File.separator);

        return datePath;
    }
    private static void makeDir(String path) {

        File dir = new File(path);

        if (!dir.exists()) {
            try {
                boolean result = dir.mkdir();

                if (result) {
                    System.out.println("Directory created");
                } else {
                    System.out.println("Failed to create directory");
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

}

