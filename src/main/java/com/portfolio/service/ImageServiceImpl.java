package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    @Override
    public void insert(ImageVO imageVO) {
        imageMapper.insert(imageVO);
    }
}
