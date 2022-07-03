package com.portfolio.service;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.CategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.SearchVO;

import java.sql.SQLException;
import java.util.List;

public interface QuestionService {
    Integer create(QuestionVO questionVO) throws SQLException;
    QuestionVO select(Integer quesNo) throws SQLException;
    void update(QuestionVO questionVO) throws SQLException;
    void delete(Integer postNo) throws SQLException;
    Integer selectLastQuesNo() throws SQLException;
    List<QuestionVO> selectList(QuestionVO questionVO) throws SQLException;
    List<QAPairVO> selectPairList(QuestionVO questionVO) throws SQLException;
    List<CategoryVO> selectCategories() throws SQLException;
    List<QuestionVO> searchQuestions(SearchVO searchVO) throws SQLException;
    List<QAPairVO> searchPairs(SearchVO searchVO) throws SQLException;
    Integer countQuestions(QuestionVO questionVO) throws SQLException;


}
