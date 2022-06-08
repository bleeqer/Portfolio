package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    long create(AnswerVO answerVO);
    AnswerVO select(long ansNo);
    List<AnswerVO> selectAnswers(AnswerVO answerVO);
    long countAnswers(long quesNo);
    List<QAPairVO> selectAnsweredPairByUser(String email);
    void update(AnswerVO answerVO);
    void delete(AnswerVO answerVO);
    Map<String, Object> addLike(AnswerLikeVO likeVO);
    Map<String, Object> subtractLike(AnswerLikeVO likeVO);
    boolean checkAnswered(QuestionVO questionVO);
    AnswerLikeVO checkLiked(AnswerVO answerVO);
}
