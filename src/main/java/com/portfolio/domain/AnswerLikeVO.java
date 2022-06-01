package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerLikeVO {

    private int likeNo;
    private int ansNo;
    private String userEmail;
    private String likeType;
}
