package com.portfolio.mapper;

import com.portfolio.domain.CommentVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AnswerCommentMapper {

    int insert(CommentVO commentVO);
    CommentVO select(int coNo);
    int update(CommentVO commentVO);
    int delete(int coNo);
    List<CommentVO> selectList(CommentVO commentVO);
    int selectLastCoNo(int ansNo);
    void addLikeCnt(int coNo);

}
