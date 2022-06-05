package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerLikeVO {

    private Long likeNo;
    private Long ansNo;
    private String userEmail;
    private String likeType;
}
