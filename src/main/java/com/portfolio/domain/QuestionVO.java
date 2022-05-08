package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class QuestionVO {

    private int quesNo;
    private String question;
    private String categoryFullPath;
    private int categoryCode;
    private String content;
    private String writer;
    private String regDate;
    private String answered;
    private int viewCnt;

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(Date date) {
        this.regDate = TimeDiff.calculateTime(date);
    }

}


