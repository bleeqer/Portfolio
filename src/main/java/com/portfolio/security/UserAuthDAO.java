package com.portfolio.security;

import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDAO {

    @Autowired
    private UserMapper userMapper;

    public CustomUserDetails getUserById(String userId) {

        return userMapper.select(userId);

    }

}
