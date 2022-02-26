package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageVO {

    // 외래키(게시글 번호)
    private int bno;

    // 이미지 업로드 경로
    private String uploadPath;

}


