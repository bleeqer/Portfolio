package com.portfolio.mapper;

import com.portfolio.domain.ImageVO;

import java.util.List;

public interface AnswerImageMapper {

    void insert(ImageVO imageVO);
    void delete(ImageVO imageVO);
    List<ImageVO> selectAll(int bno);
}