package com.portfolio.mapper;

import com.portfolio.domain.QuestionVO;
import java.util.List;

public interface QuestionMapper {

    int insert(QuestionVO questionVO);
    QuestionVO select(int postNo);
    void update(QuestionVO  questionVO);
    void delete(int postNo);
    List<QuestionVO> selectAll();
    void addViewCnt(int postNo);
    void addLikeCnt(int postNo);
    List<QuestionVO> selectMore(int startQuesNo);
}
