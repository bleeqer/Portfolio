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
    long countAnswers(long quesNo);
    List<QAPairVO> selectAnsweredPairByUser(String email);
    long addLike(AnswerLikeVO likeVO);
    long deleteLike(AnswerLikeVO likeVO);
    AnswerLikeVO findLike(AnswerLikeVO likeVO);
    long updateLike(AnswerLikeVO likeVO);
    Map<String, Object> countLike(long ansNo);
    void updateCommentCnt(Map<String, Long> map);
    boolean checkAnswered(QuestionVO questionVO);
    AnswerLikeVO checkLiked(AnswerVO answerVO);


}
