package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeVO {

    private Integer likeNo;
    private Integer coNo;
    private String userEmail;
    private String likeType;
    private Integer likes;
    private Integer dislikes;
}
