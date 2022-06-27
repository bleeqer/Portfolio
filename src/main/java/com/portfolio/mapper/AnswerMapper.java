package com.portfolio.mapper;

import com.portfolio.domain.*;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {

    AnswerVO select(long ansNo);
    long insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(long ansNo);
    List<AnswerVO> selectAnswers(AnswerVO answerVO);
    long countAnswers(AnswerVO answerVO);
    long addLike(AnswerLikeVO likeVO);
    long deleteLike(AnswerLikeVO likeVO);
    AnswerLikeVO findLike(AnswerLikeVO likeVO);
    long updateLike(AnswerLikeVO likeVO);
    Map<String, Long> countLike(long ansNo);
    boolean checkAnswered(QuestionVO questionVO);
    AnswerLikeVO checkLiked(AnswerVO answerVO);


}
