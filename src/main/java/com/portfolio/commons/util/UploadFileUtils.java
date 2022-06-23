package com.portfolio.commons.util;

import com.portfolio.domain.ImageVO;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class UploadFileUtils {

    public static List<String> uploadFile(MultipartHttpServletRequest mtfRequest) throws IOException {

        // multipartFile 리스트
        List<MultipartFile> mtfs = mtfRequest.getFiles("image");

        System.out.println("사이즈: " + mtfs.size());

        List<String> imagePathList = new ArrayList<>();

        for (MultipartFile mtf : mtfs) {

            if (!detectFileType(mtf)) {
                System.out.println("이미지 파일 아님");
            }

            // 파일 이름
            String originalFileName = mtf.getOriginalFilename();
            System.out.println("파일이름: " + originalFileName);
            // 공백문자를 언더스코어로 교체하기
            originalFileName = originalFileName.replace(' ', '_');

            // 조회시 과부하를 막기 위한 경로 구분용 현재 날짜를 yyyyMMdd 형태로 반환받기
            String date = getTodayDate();

            // 업로드 경로
            String savePath = mtfRequest.getSession().getServletContext().getRealPath(File.separator + "WEB-INF" + File.separator + "uploadedImages" + File.separator + date + File.separator);

            // 파일 이름 중복문제를 해결하기 위한 UUID
            String uuid = UUID.randomUUID().toString();

            // 현재날짜 + UUID + "_" + 파일
            String fileName = uuid + "_" + originalFileName;

            File saveFolder = new File(savePath);

            // 해당 경로가 없을경우 디렉토리를 생성
            if (!saveFolder.exists()) {
                try{
                    if(saveFolder.mkdirs()) {
                        System.out.println("폴더가 생성되었습니다.");
                    } else {
                        System.out.println("폴더 생성에 실패했습니다.");
                    }
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            } else {
                System.out.println("이미 폴더가 생성되어 있습니다.");
            }

            File saveFullPath = new File(savePath + File.separator + fileName);

            // 파일 경로에 저장하기
            mtf.transferTo(saveFullPath);

            // 반환할 이미지 url 경로 담기
            imagePathList.add(File.separator + date + File.separator + fileName);

        }

        return imagePathList;  // 이미지 url 경로 리스트 반환

    }

    public static void deleteFile(HttpServletRequest request, List<ImageVO> imgList) {

        String uploadPath = request.getSession().getServletContext().getRealPath(File.separator + "WEB-INF");

        for (ImageVO imageVO : imgList) {

            String filePath = uploadPath + imageVO.getImagePath();

            File file = new File(filePath);

            if (file.exists()) {
                boolean res = file.delete();

                if (res) {
                    System.out.println("파일 삭제 성공");
                } else {
                    System.out.println("파일 삭제 실패");
                }
            }
        }

    }

    public static String getTodayDate() {

        // Calendar 객체 생성
        Calendar cal = Calendar.getInstance();

        // 날짜 담을 변수
        String datePath;

        // 날짜
        datePath = String.format("%04d%02d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

        return datePath;
    }
//    private static void makeDir(String path) {
//
//        File dir = new File(path);
//
//        if (!dir.exists()) {
//            try {
//                boolean result = dir.mkdir();
//
//                if (result) {
//                    System.out.println("Directory created");
//                } else {
//                    System.out.println("Failed to create directory");
//                }
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
//    }

    private static boolean detectFileType(MultipartFile mtf) throws IOException {
        Tika tika = new Tika();

        InputStream fileInputStream = mtf.getInputStream();

        String mimeType = tika.detect(fileInputStream);

        return mimeType.startsWith("image");
    }

}

