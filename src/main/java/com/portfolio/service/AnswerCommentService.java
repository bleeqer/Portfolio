package com.portfolio.service;

import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;

import java.sql.SQLException;
import java.util.List;

public interface AnswerCommentService {
    int insert(CommentVO commentVO) throws SQLException;
    CommentVO select(Integer coNo) throws SQLException;
    void update(CommentVO commentVO) throws SQLException;
    void delete(CommentVO commentVO) throws SQLException;
    List<CommentVO> selectList(CommentVO commentVO) throws SQLException;
    Integer selectLastCoNo(Integer ansNo) throws SQLException;
    CommentLikeVO addLike(CommentLikeVO likeVO) throws SQLException;
    CommentLikeVO subtractLike(CommentLikeVO likeVO) throws SQLException;
    public CommentLikeVO checkLiked(CommentVO commentVO) throws SQLException;

}
