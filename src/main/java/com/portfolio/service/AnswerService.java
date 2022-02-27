package com.portfolio.service;

import com.portfolio.domain.AnswerVO;

import java.util.List;

public interface AnswerService {
    void create(AnswerVO answerVO);
    AnswerVO read(int postNo);
    void update(AnswerVO answerVO);
    void delete(int postNo);
    List<AnswerVO> readAll();
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
