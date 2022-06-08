package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.AnswerLikeVO;

import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    long insert(CommentVO commentVO);
    CommentVO select(long coNo);
    long update(CommentVO commentVO);
    long delete(long coNo);
    List<CommentVO> selectList(CommentVO commentVO);
    long selectLastCoNo(long ansNo);
    long addLike(CommentLikeVO likeVO);
    long deleteLike(CommentLikeVO likeVO);
    CommentLikeVO findLike(CommentLikeVO likeVO);
    long updateLike(CommentLikeVO likeVO);
    Map<String, Long> countLike(long coNo);
    CommentLikeVO checkLiked(CommentVO commentVO);

}
