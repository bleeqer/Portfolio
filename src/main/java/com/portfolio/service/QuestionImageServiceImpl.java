package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.QuestionImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionImageServiceImpl implements QuestionImageService {

    @Autowired
    QuestionImageMapper imageMapper;

    @Override
    public void save(ImageVO vo) {
        imageMapper.insert(vo);
    }

    @Override
    public void delete(ImageVO vo) {
        imageMapper.delete(vo);
    }

    @Override
    public List<ImageVO> readAll(int bno) {

        return imageMapper.selectAll(bno);
    }

}