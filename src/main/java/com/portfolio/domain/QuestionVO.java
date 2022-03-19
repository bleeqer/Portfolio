package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionVO {

    private int quesNo;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private int viewCnt;
    private int likeCnt;
    private List<String> imageList;

}


