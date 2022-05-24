package com.portfolio.mapper;

import com.portfolio.domain.CommentVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    int insert(CommentVO answerReply);
    void update(CommentVO answerReply);
    void delete(int coNo);
    List<CommentVO> selectAll();
    void addLikeCnt(int replyNo);
    CommentVO selectOne(int replyNo);
    List<CommentVO> selectList(CommentVO commentVO);
    int selectLastCoNo(int ansNo);
//    int selectNestedMaxOrder(int parentCommentNo);
//    int selectMaxOrder();
}
