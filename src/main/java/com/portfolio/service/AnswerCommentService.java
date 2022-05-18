package com.portfolio.service;

import com.portfolio.domain.CommentVO;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;

public interface AnswerCommentService {
    void insert(CommentVO commentVO);
    CommentVO readOne(int ansNo);
    List<CommentVO> selectList(CommentVO commentVO);
    List<CommentVO> readMoreList(HashMap<String, Integer> map);
    void update(CommentVO answerReply);
    void delete(HashMap<String, Integer> postNo);
    List<CommentVO> readAll();
    void addLikeCnt(int postNo);
    int selectNestedMaxOrder(int parentCommentNo);
    int selectMaxOrder();
}
