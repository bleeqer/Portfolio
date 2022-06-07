package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionVO {

    private Long quesNo;
    private String question;
    private Long categoryCode;
    private String categoryName;
    private String userEmail;
    private String regDate;
    private String answered;
    private Long viewCnt;
    private Long answerCnt;

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(Date date) {
        this.regDate = TimeDiff.calculateTime(date);
    }

}


