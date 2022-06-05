package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeVO {

    private Long likeNo;
    private Long coNo;
    private String userEmail;
    private String likeType;
}
