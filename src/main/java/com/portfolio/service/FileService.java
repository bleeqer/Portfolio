package com.portfolio.service;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.FileVO;

import java.util.List;

public interface FileService {
    void save(FileVO vo);
    void delete(FileVO vo);
    List<FileVO> readAll(int bno);
}
