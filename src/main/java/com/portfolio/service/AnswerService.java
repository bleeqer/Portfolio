package com.portfolio.service;

import com.portfolio.domain.AnswerVO;

import java.util.List;

public interface AnswerService {
    int create(AnswerVO answerVO);
    AnswerVO readOne(int ansNo);
    List<AnswerVO> selectAnswers(int quesNo);
    List<AnswerVO> selectAnsweredPairByUser(int userNo);
    List<AnswerVO> readMoreList(AnswerVO answer);
    void update(AnswerVO answerVO);
    void delete(int postNo);
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
