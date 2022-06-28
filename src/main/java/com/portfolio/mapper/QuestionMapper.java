package com.portfolio.mapper;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.SearchVO;

import java.util.List;

public interface QuestionMapper {

    Integer insert(QuestionVO questionVO);
    QuestionVO select(Integer quesNo);
    void update(QuestionVO  questionVO);
    void delete(Integer postNo);
    Integer selectLastQuesNo();
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    void addViewCnt(Integer quesNo);
    void updateAnswered(QuestionVO questionVO);
    List<CategoryVO> selectCategories();
    List<QuestionVO> searchList(SearchVO searchVO);
    List<QAPairVO> searchPairs(SearchVO searchVO);
    Integer countQuestions(QuestionVO questionVO);


}
