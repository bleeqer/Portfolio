package com.portfolio.mapper;

import com.portfolio.domain.*;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {

    AnswerVO select(int ansNo);
    int insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(int ansNo);
    List<AnswerVO> selectAnswers(Map<String, Integer> map);
    int countAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(String email);
    int addLike(AnswerLikeVO likeVO);
    int deleteLike(AnswerLikeVO likeVO);
    AnswerLikeVO findLike(AnswerLikeVO likeVO);
    int updateLike(AnswerLikeVO likeVO);
    Map<String, Integer> countLike(int ansNo);
    void updateCommentCnt(Map<String, Integer> map);


}
