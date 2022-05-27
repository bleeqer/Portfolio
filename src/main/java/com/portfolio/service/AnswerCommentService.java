package com.portfolio.service;

import com.portfolio.domain.CommentVO;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;

public interface AnswerCommentService {
    void insert(CommentVO commentVO);
    void delete(int coNo);
    CommentVO readOne(int ansNo);
    List<CommentVO> selectList(CommentVO commentVO);
    int selectLastCoNo(int ansNo);
    void update(CommentVO answerReply);

    List<CommentVO> readAll();
//    void addLikeCnt(int postNo);
//    int selectNestedMaxOrder(int parentCommentNo);
//    int selectMaxOrder();
}
