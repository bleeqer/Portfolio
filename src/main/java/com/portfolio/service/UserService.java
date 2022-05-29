package com.portfolio.service;

import com.portfolio.domain.UserDetailsVO;
import com.portfolio.domain.UserVO;
import com.portfolio.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void create(UserVO userVO);
    CustomUserDetails selectForAuth(String username);
    UserVO select(String email);
    void update(UserVO userVO);
    void delete(String userNo);

}
