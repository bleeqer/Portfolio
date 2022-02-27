package com.portfolio.service;

import com.portfolio.domain.ImageVO;

import java.util.List;

public interface QuestionImageService {

    void save(ImageVO vo);
    void delete(ImageVO vo);
    List<ImageVO> readAll(int bno);

}
