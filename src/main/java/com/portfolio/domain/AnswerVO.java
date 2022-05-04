package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AnswerVO {

    private int quesNo;
    private int ansNo;
    private String answer;
    private int userNo;
    private String regDate;
    private int likeCnt;
    private String userEmail;
    private String userName;
    private String userPhoto;

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(Date date) {
        this.regDate = TimeDiff.calculateTime(date);
    }

}


