package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerLikeVO {

    private Integer likeNo;
    private Integer ansNo;
    private String userEmail;
    private String likeType;
    private Integer likes;
    private Integer dislikes;
}
