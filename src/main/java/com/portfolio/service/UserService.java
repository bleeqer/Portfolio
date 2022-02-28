package com.portfolio.service;

import com.portfolio.domain.UserVO;

public interface UserService {
    void create(UserVO userVO);
    UserVO read(String userId);
    void update(UserVO userVO);
    void delete(String userId);

}
