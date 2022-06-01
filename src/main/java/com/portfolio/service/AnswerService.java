package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.domain.QAPairVO;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    int create(AnswerVO answerVO);
    AnswerVO select(int ansNo);
    List<AnswerVO> selectAnswers(Map<String, Integer> map);
    int countAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(String email);
    void update(AnswerVO answerVO);
    void delete(AnswerVO answerVO);
    Map<String, Integer> addLike(AnswerLikeVO likeVO);
    Map<String, Integer> subtractLike(AnswerLikeVO likeVO);
}
