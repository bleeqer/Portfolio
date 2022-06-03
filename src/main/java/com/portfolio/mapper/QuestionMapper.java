package com.portfolio.mapper;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface QuestionMapper {

    int insert(QuestionVO questionVO);
    QuestionVO select(int quesNo);
    void update(QuestionVO  questionVO);
    void delete(int postNo);
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    void addViewCnt(int quesNo);
    void updateAnswered(QuestionVO questionVO);
    List<CategoryVO> selectCategories();


}
