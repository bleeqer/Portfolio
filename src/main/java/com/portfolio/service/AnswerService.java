package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QAPairVO;

import java.util.List;

public interface AnswerService {
    int create(AnswerVO answerVO);
    AnswerVO select(int ansNo);
    List<AnswerVO> selectAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(int userNo);
    void update(AnswerVO answerVO);
    void delete(int postNo);
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
