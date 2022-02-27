package com.portfolio.service;

import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface QuestionService {
    void create(QuestionVO questionVO);
    QuestionVO read(int postNo);
    void update(QuestionVO questionVO);
    void delete(int postNo);
    List<QuestionVO> readAll();
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
}
