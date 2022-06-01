package com.portfolio.mapper;

import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.AnswerLikeVO;

import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    int insert(CommentVO commentVO);
    CommentVO select(int coNo);
    int update(CommentVO commentVO);
    int delete(int coNo);
    List<CommentVO> selectList(CommentVO commentVO);
    int selectLastCoNo(int ansNo);
    int addLike(CommentLikeVO likeVO);
    int deleteLike(CommentLikeVO likeVO);
    CommentLikeVO findLike(CommentLikeVO likeVO);
    int updateLike(CommentLikeVO likeVO);
    Map<String, Integer> countLike(int ansNo);

}
