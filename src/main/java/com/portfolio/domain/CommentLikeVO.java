package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeVO {

    private int likeNo;
    private int coNo;
    private String userEmail;
    private String likeType;
}
