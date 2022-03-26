package com.portfolio.service;

import com.portfolio.domain.AnswerReplyVO;

import java.util.HashMap;
import java.util.List;

public interface AnswerReplyService {
    int create(AnswerReplyVO answerReply);
    AnswerReplyVO readOne(int ansNo);
    List<AnswerReplyVO> readList(int quesNo);
    List<AnswerReplyVO> readMoreList(int quesNo);
    void update(AnswerReplyVO answerReply);
    void delete(HashMap<String, Integer> postNo);
    List<AnswerReplyVO> readAll();
    void addLikeCnt(int postNo);
    int selectNestedMaxOrder(int parentCommentNo);
    int selectMaxOrder();
}
