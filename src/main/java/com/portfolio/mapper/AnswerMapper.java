package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.LikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface AnswerMapper {

    AnswerVO select(int ansNo);
    int insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(int ansNo);
    List<AnswerVO> selectAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(int userNo);
    void addViewCnt(int ansNo);
    void addLikeCnt(LikeVO likeVO);
    void addCommentCnt(int ansNo);

}
