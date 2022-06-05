package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QAPairVO {

    // question
    private long quesNo;
    private String question;
    private String categoryName;

    // answer
    private long ansNo;
    private String answer;
    private String answerRegDate;
    private long likes;
    private long dislikes;
    private long commentCnt;
    private long userNo;
    private String userEmail;
    private String userName;
    private String userPhoto;
    private String userOccupation;

    public String getAnswerRegDate() {
        return answerRegDate;
    }
    public void setAnswerRegDate(Date date) {
        this.answerRegDate = TimeDiff.calculateTime(date);
    }

}
