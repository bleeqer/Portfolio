package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    @Override
    public void insert(ImageVO imageVO) throws SQLException {

        try {

            imageMapper.insert(imageVO);

        } catch (Exception e) {

            throw new SQLException("이미지 등록에 실패했습니다.");

        }
    }
}
