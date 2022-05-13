package com.portfolio.service;

import com.portfolio.domain.QuestionCategoryVO;
import com.portfolio.mapper.QuestionCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

    @Autowired
    QuestionCategoryMapper questionCategoryMapper;

    @Override
    public List<QuestionCategoryVO> selectAll() {
        return questionCategoryMapper.selectAll();
    }

}
