package com.portfolio.service;

import com.portfolio.domain.CommentVO;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;

public interface AnswerCommentService {
    void insert(CommentVO commentVO);
    CommentVO readOne(int ansNo);
    List<CommentVO> selectList(CommentVO commentVO);
    void update(CommentVO answerReply);
    void delete(int coNo);
    List<CommentVO> readAll();
    void addLikeCnt(int postNo);
    int selectNestedMaxOrder(int parentCommentNo);
    int selectMaxOrder();
}
