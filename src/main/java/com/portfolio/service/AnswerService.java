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
    AnswerVO select(Integer ansNo) throws SQLException;
    List<AnswerVO> selectAnswers(AnswerVO answerVO) throws SQLException;
    Integer countAnswers(AnswerVO answerVO) throws SQLException;
    void update(AnswerVO answerVO) throws SQLException;
    void delete(AnswerVO answerVO) throws SQLException;
    AnswerLikeVO addLike(AnswerLikeVO likeVO) throws SQLException;
    AnswerLikeVO subtractLike(AnswerLikeVO likeVO) throws SQLException;
    boolean checkAnswered(QuestionVO questionVO) throws SQLException;
    AnswerLikeVO checkLiked(AnswerVO answerVO) throws SQLException;
}
