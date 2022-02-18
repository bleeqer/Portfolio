package com.portfolio.service;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.FileVO;
import com.portfolio.mapper.BoardMapper;
import com.portfolio.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void save(FileVO vo) {
        fileMapper.insert(vo);
    }

    @Override
    public void delete(FileVO vo) {
        fileMapper.delete(vo);
    }

    @Override
    public List<FileVO> readAll(int bno) {

        return fileMapper.selectAll(bno);
    }

}
