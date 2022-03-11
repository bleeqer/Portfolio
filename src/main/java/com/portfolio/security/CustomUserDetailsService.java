package com.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserAuthDAO userAuthDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        CustomUserDetails user = userAuthDAO.getUserById(userId);

        System.out.println("유저아이디: " + user.getUsername());
        System.out.println("유저비밀번호: " + user.getPassword());

        if (user.getUsername().isEmpty()) {

            throw new UsernameNotFoundException(userId);

        }

        return user;

    }

}

