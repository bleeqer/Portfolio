package com.portfolio.controller;

import com.portfolio.commons.exceptions.NotImageFileException;
import com.portfolio.commons.util.UploadFileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("file")
public class ImageController {

    @PostMapping(value="upload")
    public ResponseEntity<List<String>> uploadFile(MultipartHttpServletRequest mtfRequest) throws Exception {

        // 파일(들)을 지정된 경로에 저장 및 경로 리스트 반환
        try {
            List<String> attachedImgs = UploadFileUtils.uploadFile(mtfRequest);

            // Image DTO 리스트와 함께 OK status response code 반환
            return new ResponseEntity<>(attachedImgs, HttpStatus.OK);

        } catch(NotImageFileException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }

}
