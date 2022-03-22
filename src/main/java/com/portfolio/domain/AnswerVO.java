package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AnswerVO {

    private int quesNo;
    private int ansNo;
    private String content;
    private String writer;
    private String regDate;
    private int viewCnt;
    private int likeCnt;
    private List<String> imageList;

}


