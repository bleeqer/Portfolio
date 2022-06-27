package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVO {

    // Comment
    private Long level;
    private Long coNo;
    private Long ansNo;
    private String answerComment;
    private Long parentCoNo;
    private String regDate;
    private Long likes;
    private Long dislikes;

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


