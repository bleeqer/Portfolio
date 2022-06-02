package com.portfolio.service;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionCategoryVO;
import com.portfolio.domain.QuestionVO;

import java.util.HashMap;
import java.util.List;

public interface QuestionService {
    int create(QuestionVO questionVO);
    QuestionVO select(int quesNo);
    void update(QuestionVO questionVO);
    void delete(int postNo);
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    List<QuestionCategoryVO> selectCategories();


}
