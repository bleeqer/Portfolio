package com.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserAuthDAO userAuthDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws InternalAuthenticationServiceException {

        CustomUserDetails user = userAuthDAO.getUserById(userId);

        if (user == null) {
            throw new InternalAuthenticationServiceException(userId);
        }

        return user;

    }

}

