package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AnswerVO {

    private Integer quesNo;
    private Integer ansNo;
    private String answer;
    private String regDate;
    private Integer likes;
    private Integer dislikes;
    private Integer commentCnt;
    private String userEmail;
    private String userName;
    private String userPhoto;
    private String userOccupation;

    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(Date date) {
        this.regDate = TimeDiff.calculateTime(date);
    }

}


