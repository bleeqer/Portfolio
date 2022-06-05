package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVO {

    // Comment
    private long level;
    private long coNo;
    private long ansNo;
    private String answerComment;
    private long parentCoNo;
    private long lastCoNo;
    private String regDate;
    private long likes;
    private long dislikes;

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


