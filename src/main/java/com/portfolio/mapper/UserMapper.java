package com.portfolio.mapper;

import com.portfolio.domain.UserVO;

public interface UserMapper {

    int insert(UserVO userVO);
    UserVO select(String userId);
    void update(UserVO userVO);
    void delete(String userId);

}
