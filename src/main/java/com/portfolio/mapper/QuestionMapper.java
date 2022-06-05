package com.portfolio.mapper;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;

import java.util.List;

public interface QuestionMapper {

    long insert(QuestionVO questionVO);
    QuestionVO select(long quesNo);
    void update(QuestionVO  questionVO);
    void delete(long postNo);
    long selectLastQuesNo();
    List<QuestionVO> selectList(QuestionVO questionVO);
    List<QAPairVO> selectPairList(QuestionVO questionVO);
    void addViewCnt(long quesNo);
    void updateAnswered(QuestionVO questionVO);
    List<CategoryVO> selectCategories();


}
