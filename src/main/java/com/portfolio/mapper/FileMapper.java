package com.portfolio.mapper;

import com.portfolio.domain.FileVO;

import java.util.List;

public interface FileMapper {

    void insert(FileVO fileVO);
    void delete(FileVO fileVO);
    List<FileVO> selectAll(int bno);
}
