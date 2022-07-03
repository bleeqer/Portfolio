package com.portfolio.service;

import com.portfolio.domain.ImageVO;

import java.sql.SQLException;

public interface ImageService {

    void insert(ImageVO imageVO) throws SQLException;
}
