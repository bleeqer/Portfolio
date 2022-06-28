package com.portfolio.mapper;

import com.portfolio.domain.*;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {

    AnswerVO select(Integer ansNo);
    Integer insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(Integer ansNo);
    List<AnswerVO> selectAnswers(AnswerVO answerVO);
    Integer countAnswers(AnswerVO answerVO);
    Integer addLike(AnswerLikeVO likeVO);
    Integer deleteLike(AnswerLikeVO likeVO);
    AnswerLikeVO findLike(AnswerLikeVO likeVO);
    Integer updateLike(AnswerLikeVO likeVO);
    AnswerLikeVO countLike(Integer ansNo);
    void updateCommentCnt(Map<String, Integer> map);
    boolean checkAnswered(QuestionVO questionVO);
    AnswerLikeVO checkLiked(AnswerVO answerVO);


}
