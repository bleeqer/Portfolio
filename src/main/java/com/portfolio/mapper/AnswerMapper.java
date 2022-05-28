package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.LikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {

    AnswerVO select(int ansNo);
    int insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(int ansNo);
    List<AnswerVO> selectAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(int userNo);
    void addViewCnt(int ansNo);
    int addLike(LikeVO likeVO);
    int deleteLike(LikeVO likeVO);
    LikeVO findLike(LikeVO likeVO);
    LikeVO updateLike(LikeVO likeVO);
    Map<String, Integer> countLike(int ansNo);
    void updateCommentCnt(@Param("ansNo") int ansNo, @Param("coCnt") int coCnt);


}
