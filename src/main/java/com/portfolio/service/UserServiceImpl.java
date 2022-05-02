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
    public CustomUserDetails selectForAuth(String username) {
        return userMapper.selectForAuth(username);

    }

    @Override
    public UserVO select(int userNo) {
        return userMapper.select(userNo);

    }

    @Override
    public void update(UserVO userVO) {

        userMapper.update(userVO);

    }

    @Override
    public void delete(String userNo) {

        userMapper.delete(userNo);

    }

}
