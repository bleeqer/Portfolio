package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QAPairVO {

    private String question;
    private String catPath;
    private String questionRegDate;
    private int questionLikeCnt;

    private int quesNo;
    private int ansNo;
    private String answer;
    private String answerUserNo;
    private String answerRegDate;
    private int answerLikeCnt;
    private String answerEmail;
    private String answerUserName;
    private String answerUserPhoto;


}
