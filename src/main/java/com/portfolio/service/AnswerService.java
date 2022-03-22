package com.portfolio.service;

import com.portfolio.domain.AnswerVO;

import java.util.List;

public interface AnswerService {
    int create(AnswerVO answerVO);
    AnswerVO readOne(int ansNo);
    List<AnswerVO> readList(int quesNo);
    void update(AnswerVO answerVO);
    void delete(int postNo);
    List<AnswerVO> readAll();
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
