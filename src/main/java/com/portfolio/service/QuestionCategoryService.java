package com.portfolio.service;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.domain.QuestionCategoryVO;

import java.util.HashMap;
import java.util.List;

public interface QuestionCategoryService {
    List<QuestionCategoryVO> selectAll();
    String selectFullPath(int catCode);
}
