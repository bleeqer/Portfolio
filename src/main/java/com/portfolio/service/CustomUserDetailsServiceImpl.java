package com.portfolio.service;

import com.portfolio.mapper.UserMapper;
import com.portfolio.domain.CustomUserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws AuthenticationException {

        CustomUserDetailsVO user = userMapper.selectForAuth(userId);

        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }

        return user;

    }

    public void create(CustomUserDetailsVO userVO) {

        userMapper.insert(userVO);

    }

    public CustomUserDetailsVO select(String email) {
        return userMapper.select(email);
    }

    public void update(CustomUserDetailsVO userVO) {

        userMapper.update(userVO);

    }

    public void delete(String userNo) {

        userMapper.delete(userNo);

    }

}

