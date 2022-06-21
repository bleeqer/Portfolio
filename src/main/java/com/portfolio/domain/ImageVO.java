package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageVO {

    private long imgNo;

    private long ansNo;

    // 이미지 업로드 경로
    private String imagePath;

}


