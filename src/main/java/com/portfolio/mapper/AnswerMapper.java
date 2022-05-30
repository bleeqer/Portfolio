package com.portfolio.mapper;

import com.portfolio.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.HashMap;
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
    int addLike(LikeVO likeVO);
    int deleteLike(LikeVO likeVO);
    LikeVO findLike(LikeVO likeVO);
    int updateLike(LikeVO likeVO);
    Map<String, Integer> countLike(int ansNo);
    void updateCommentCnt(Map<String, Integer> map);


}
