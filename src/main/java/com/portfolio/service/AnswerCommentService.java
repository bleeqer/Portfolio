package com.portfolio.service;

import com.portfolio.domain.CommentVO;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;

public interface AnswerCommentService {
    void insert(CommentVO commentVO);
    CommentVO select(int coNo);
    int update(CommentVO commentVO);
    void delete(CommentVO commentVO);
    List<CommentVO> selectList(CommentVO commentVO);
    int selectLastCoNo(int ansNo);

}
