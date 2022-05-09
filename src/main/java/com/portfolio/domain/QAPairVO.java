package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QAPairVO {

    private int quesNo;
    private String question;
    private String categoryName;
    private int ansNo;
    private String answer;
    private String answerRegDate;
    private int answerLikeCnt;
    private int answerUserNo;
    private String answerUserEmail;
    private String answerUserName;
    private String answerUserPhoto;
    private String answerUserOccupation;

//    public String getAnswerRegDate() {
//        return answerRegDate;
//    }
//    public void setAnswerRegDate(Date date) {
//        this.answerRegDate = TimeDiff.calculateTime(date);
//    }

}
