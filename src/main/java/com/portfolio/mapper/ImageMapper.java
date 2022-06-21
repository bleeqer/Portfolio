package com.portfolio.mapper;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.ImageVO;

import java.util.List;

public interface ImageMapper {

    void insert(ImageVO imageVO);
    void delete(AnswerVO answerVO);
    List<ImageVO> selectAll(long bno);
    List<ImageVO> selectOldFiles();
}
