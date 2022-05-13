package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVO {

    // Comment
    private int level;
    private int coNo;
    private int ansNo;
    private String answerComment;
    private String parentCoNo;
    private String regDate;
    private int likeCnt;

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


