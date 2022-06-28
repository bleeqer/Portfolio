package com.portfolio.service;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.SearchVO;

import java.util.List;

public interface QuestionService {
    Integer create(QuestionVO questionVO);
    QuestionVO select(Integer quesNo);
    void update(QuestionVO questionVO);
    void delete(Integer postNo);
    Integer selectLastQuesNo();
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    List<CategoryVO> selectCategories();
    List<QuestionVO> searchQuestions(SearchVO searchVO);
    List<QAPairVO> searchPairs(SearchVO searchVO);
    Integer countQuestions(QuestionVO questionVO);


}
