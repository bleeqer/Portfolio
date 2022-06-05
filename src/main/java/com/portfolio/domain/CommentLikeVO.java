package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeVO {

    private long likeNo;
    private long coNo;
    private String userEmail;
    private String likeType;
}
