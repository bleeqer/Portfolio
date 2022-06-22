package com.portfolio.service;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.SearchVO;

import java.util.List;

public interface QuestionService {
    long create(QuestionVO questionVO);
    QuestionVO select(long quesNo);
    void update(QuestionVO questionVO);
    void delete(long postNo);
    long selectLastQuesNo();
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    List<CategoryVO> selectCategories();
    List<QuestionVO> searchQuestions(SearchVO searchVO);
    List<QAPairVO> searchPairs(SearchVO searchVO);
    long countQuestions(QuestionVO questionVO);


}
