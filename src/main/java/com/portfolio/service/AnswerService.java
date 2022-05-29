package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.LikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    int create(AnswerVO answerVO);
    AnswerVO select(int ansNo);
    List<AnswerVO> selectAnswers(Map<String, Integer> map);
    int countAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(int userNo);
    void update(AnswerVO answerVO);
    void delete(AnswerVO answerVO);
    Map<String, Integer> addLike(LikeVO likeVO);
    Map<String, Integer> subtractLike(LikeVO likeVO);
}
