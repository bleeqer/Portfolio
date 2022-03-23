package com.portfolio.mapper;

import com.portfolio.domain.AnswerReplyVO;

import java.util.List;

public interface AnswerReplyMapper {

    int insert(AnswerReplyVO answerReply);
    void update(AnswerReplyVO answerReply);
    void delete(int replyNo);
    List<AnswerReplyVO> selectAll();
    void addLikeCnt(int replyNo);
    AnswerReplyVO selectOne(int replyNo);
    List<AnswerReplyVO> selectList(int ansNo);
    List<AnswerReplyVO> selectMoreList(int ansNo);
}
