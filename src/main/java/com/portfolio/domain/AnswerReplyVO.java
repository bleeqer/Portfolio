package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerReplyVO {

    private int ansNo;
    private int replyNo;
    private String content;
    private String writer;
    private String regDate;
    private int likeCnt;

}


