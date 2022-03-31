package com.portfolio.mapper;

import com.portfolio.domain.QuestionCategoryVO;

import java.util.List;

public interface QuestionCategoryMapper {

    List<QuestionCategoryVO> selectAll();
}
