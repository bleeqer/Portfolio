package com.portfolio.service;

import com.portfolio.domain.QuestionVO;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {
    void create(QuestionVO questionVO);
    QuestionVO read(int postNo);
    void update(QuestionVO questionVO);
    void delete(int postNo);
    List<QuestionVO> readAll();
    List<QuestionVO> readAllByTopic(String topic);
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
    List<QuestionVO> getMore(int startQuesNo);
    List<QuestionVO> getMoreByTopic(HashMap<String, Object> map);

}
