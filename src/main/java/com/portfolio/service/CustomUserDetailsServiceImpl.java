package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.ImageMapper;
import com.portfolio.mapper.UserMapper;
import com.portfolio.domain.CustomUserDetailsVO;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ImageMapper imageMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws AuthenticationException {

        CustomUserDetailsVO user = userMapper.selectForAuth(userId);

        if (user == null) {

            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }

        return user;

    }

    public void create(CustomUserDetailsVO userVO) throws SQLException {

        if (userMapper.checkExist(userVO.getEmail())) {

            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");

        }

        try {

            userMapper.insert(userVO);

        } catch (Exception e) {
      System.out.println(e.getMessage());
            throw new SQLException("회원가입에 실패했습니다.");

        }


    }

    public CustomUserDetailsVO select(String email) throws SQLException {

        if (!userMapper.checkExist(email)) {

            throw new IllegalArgumentException("존재하지 않는 계정입니다.");

        }

        CustomUserDetailsVO userVO;

        try {

            userVO = userMapper.select(email);

        } catch (Exception e) {

            throw new SQLException("사용자를 찾을 수 없습니다.");

        }
        return userVO;

    }

    public void update(CustomUserDetailsVO userVO) throws SQLException {

        try {

            userMapper.update(userVO);

        } catch (Exception e) {
      System.out.println(e.getMessage());
      e.getStackTrace();
            throw new SQLException("사용자 정보 업데이트에 실패했습니다.");

        }


    }

    public void delete(String userNo) throws SQLException {

        try {

            userMapper.delete(userNo);

        } catch (Exception e) {

            throw new SQLException("회원탈퇴에 실패했습니다.");

        }

    }

}

