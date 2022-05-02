package com.portfolio.mapper;

import com.portfolio.domain.QuestionVO;

import java.util.HashMap;
import java.util.List;

public interface QuestionMapper {

    int insert(QuestionVO questionVO);
    QuestionVO select(int postNo);
    void update(QuestionVO  questionVO);
    void delete(int postNo);
    List<QuestionVO> selectNotAnswered(int count);

    List<QuestionVO> selectAnswered(int count);
    List<QuestionVO> selectAllByTopic(String topic);
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
    List<QuestionVO> selectMore(int startQuesNo);
    List<QuestionVO> selectMoreByTopic(HashMap<String, Object> map);


}
