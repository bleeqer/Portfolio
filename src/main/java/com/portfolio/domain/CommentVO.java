package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVO {

    // Comment
    private Integer level;
    private Integer coNo;
    private Integer ansNo;
    private String answerComment;
    private Integer parentCoNo;
    private Integer lastCoNo;
    private String regDate;
    private int likes;
    private int dislikes;

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


