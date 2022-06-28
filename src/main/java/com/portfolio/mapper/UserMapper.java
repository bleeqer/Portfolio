package com.portfolio.mapper;

import com.portfolio.domain.CustomUserDetailsVO;

public interface UserMapper {

    void insert(CustomUserDetailsVO userVO);
    CustomUserDetailsVO selectForAuth(String username);
    CustomUserDetailsVO select(String email);
    Integer update(CustomUserDetailsVO userVO);
    void delete(String userNo);
    boolean checkExist(String email);


}
