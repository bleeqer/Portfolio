package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface AnswerMapper {

    int insert(AnswerVO answerVO);
    void update(AnswerVO answerVO);
    void delete(int ansNo);
    List<AnswerVO> selectAnswers(int quesNo);
    List<QAPairVO> selectAnsweredPairByUser(int userNo);
    void addViewCnt(int ansNo);
    void addLikeCnt(int ansNo);
    AnswerVO selectOne(int ansNo);
    List<AnswerVO> selectList(int quesNo);
    List<AnswerVO> selectMoreList(AnswerVO answer);
}
