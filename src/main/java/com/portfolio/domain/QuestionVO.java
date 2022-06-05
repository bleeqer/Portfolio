package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionVO {

    private long quesNo;
    private String question;
    private String categoryCode;
    private String categoryName;
    private String userEmail;
    private String regDate;
    private String answered;
    private long viewCnt;
    private long answerCnt;

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(Date date) {
        this.regDate = TimeDiff.calculateTime(date);
    }

}


