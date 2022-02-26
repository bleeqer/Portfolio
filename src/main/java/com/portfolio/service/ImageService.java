package com.portfolio.service;

import com.portfolio.domain.ImageVO;

import java.util.List;

public interface ImageService {
    void save(ImageVO vo);
    void delete(ImageVO vo);
    List<ImageVO> readAll(int bno);
}
