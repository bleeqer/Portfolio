package com.portfolio.service;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AnswerService {
    Integer create(AnswerVO answerVO) throws SQLException;
    AnswerVO select(Integer ansNo);
    List<AnswerVO> selectAnswers(AnswerVO answerVO);
    Integer countAnswers(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(AnswerVO answerVO);
    AnswerLikeVO addLike(AnswerLikeVO likeVO);
    AnswerLikeVO subtractLike(AnswerLikeVO likeVO);
    boolean checkAnswered(QuestionVO questionVO);
    AnswerLikeVO checkLiked(AnswerVO answerVO);
}
