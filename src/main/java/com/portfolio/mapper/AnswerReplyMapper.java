package com.portfolio.mapper;

import com.portfolio.domain.AnswerReplyVO;

import java.util.HashMap;
import java.util.List;

public interface AnswerReplyMapper {

    int insert(AnswerReplyVO answerReply);
    void update(AnswerReplyVO answerReply);
    void delete(HashMap<String, Integer> replyNo);
    List<AnswerReplyVO> selectAll();
    void addLikeCnt(int replyNo);
    AnswerReplyVO selectOne(int replyNo);
    List<AnswerReplyVO> selectList(int ansNo);
    List<AnswerReplyVO> selectMoreList(int ansNo);
    int selectNestedMaxOrder(int parentCommentNo);
    int selectMaxOrder();
}
