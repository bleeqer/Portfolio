package com.portfolio.service;

import com.portfolio.domain.UserVO;
import com.portfolio.mapper.UserMapper;
import com.portfolio.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void create(UserVO userVO) {

        userMapper.insert(userVO);

    }

    @Override
    public CustomUserDetails read(String userId) {
        System.out.println("도착?");
        return userMapper.select(userId);

    }

    @Override
    public void update(UserVO userVO) {

        userMapper.update(userVO);

    }

    @Override
    public void delete(String userId) {

        userMapper.delete(userId);

    }

}
