package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.AnswerLikeVO;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;

public interface AnswerCommentService {
    void insert(CommentVO commentVO);
    CommentVO select(Integer coNo);
    Integer update(CommentVO commentVO);
    void delete(CommentVO commentVO);
    List<CommentVO> selectList(CommentVO commentVO);
    Integer selectLastCoNo(Integer ansNo);
    Map<String, Integer> addLike(CommentLikeVO likeVO);
    Map<String, Integer> subtractLike(CommentLikeVO likeVO);
    public CommentLikeVO checkLiked(CommentVO commentVO);

}
