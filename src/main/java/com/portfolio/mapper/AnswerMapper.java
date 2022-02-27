package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface AnswerMapper {

    int insert(AnswerVO answerVO);
    AnswerVO select(int postNo);
    void update(AnswerVO answerVO);
    void delete(int postNo);
    List<AnswerVO> selectAll();
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
