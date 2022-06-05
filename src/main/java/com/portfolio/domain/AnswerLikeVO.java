package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerLikeVO {

    private long likeNo;
    private long ansNo;
    private String userEmail;
    private String likeType;
}
