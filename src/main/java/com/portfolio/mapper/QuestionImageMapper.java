package com.portfolio.mapper;

import com.portfolio.domain.ImageVO;

import java.util.List;

public interface QuestionImageMapper {

    void insert(ImageVO imageVO);
    void delete(ImageVO imageVO);
    List<ImageVO> selectAll(long bno);
}
