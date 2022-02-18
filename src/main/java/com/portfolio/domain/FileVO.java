package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileVO {

    // 외래키(게시글 번호)
    private int bno;

    // 파일 경로
    private String file;

}


