package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.AnswerLikeVO;

import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    Integer insert(CommentVO commentVO);
    CommentVO select(Integer coNo);
    Integer update(CommentVO commentVO);
    Integer delete(Integer coNo);
    List<CommentVO> selectList(CommentVO commentVO);
    Integer selectLastCoNo(Integer ansNo);
    Integer addLike(CommentLikeVO likeVO);
    Integer deleteLike(CommentLikeVO likeVO);
    CommentLikeVO findLike(CommentLikeVO likeVO);
    Integer updateLike(CommentLikeVO likeVO);
    Map<String, Integer> countLike(Integer coNo);
    CommentLikeVO checkLiked(CommentVO commentVO);

}
