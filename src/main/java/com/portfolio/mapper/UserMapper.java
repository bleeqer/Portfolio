package com.portfolio.mapper;

import com.portfolio.domain.UserDetailsVO;
import com.portfolio.domain.UserVO;
import com.portfolio.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserMapper {

    void insert(UserVO userVO);
    CustomUserDetails selectForAuth(String username);
    UserVO select(int userNo);
    void update(UserVO userVO);
    void delete(String userNo);

}
