package com.portfolio.mapper;

import com.portfolio.domain.CommentVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    int insert(CommentVO answerReply);
    void update(CommentVO answerReply);
    void delete(HashMap<String, Integer> replyNo);
    List<CommentVO> selectAll();
    void addLikeCnt(int replyNo);
    CommentVO selectOne(int replyNo);
    List<CommentVO> selectList(CommentVO commentVO);
    List<CommentVO> selectMoreList(Map<String, Integer> map);
    int selectNestedMaxOrder(int parentCommentNo);
    int selectMaxOrder();
}
