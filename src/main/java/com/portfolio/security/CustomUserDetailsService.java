package com.portfolio.security;

import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws InternalAuthenticationServiceException {

        CustomUserDetails user = userMapper.selectForAuth(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException(userId);
        }

        return user;

    }

}

