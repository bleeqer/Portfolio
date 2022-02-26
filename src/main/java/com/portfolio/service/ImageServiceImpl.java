package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageManager;

    @Override
    public void save(ImageVO vo) {
        imageManager.insert(vo);
    }

    @Override
    public void delete(ImageVO vo) {
        imageManager.delete(vo);
    }

    @Override
    public List<ImageVO> readAll(int bno) {

        return imageManager.selectAll(bno);
    }

}
