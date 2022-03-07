package com.portfolio.security;

import com.portfolio.domain.UserVO;
import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserVO userVO = userMapper.select(userId);

        String sampleRole = "";

        System.out.println("userDetails 불러와짐");

        if (userId.startsWith("user")) {

            sampleRole = "ROLE_USER";

        } else if (userId.startsWith("admin")) {

            sampleRole = "ROLE_ADMIN";

        }

        CustomUserDetails customUserDetails = new CustomUserDetails(
                userVO,
                AuthorityUtils.createAuthorityList(sampleRole)
        );

        return customUserDetails;

    }

}

