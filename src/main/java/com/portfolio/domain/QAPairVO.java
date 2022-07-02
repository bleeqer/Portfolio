package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class QAPairVO {

    // question
    private Integer quesNo;
    private String question;
    private String categoryName;

    // answer
    private Integer ansNo;
    private String answer;
    private String answerRegDate;
    private Integer likes;
    private Integer dislikes;
    private Integer commentCnt;
    private Integer userNo;
    private String userEmail;
    private String userName;
    private String userPhoto;
    private String userOccupation;
    private String searchKeyword;


    public String getAnswerRegDate() {
        return answerRegDate;
    }
    public void setAnswerRegDate(Date date) {
        this.answerRegDate = TimeDiff.calculateTime(date);
    }

}
