package com.portfolio.security;

import com.portfolio.domain.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    public CustomUserDetails(UserVO userVO, Collection<? extends GrantedAuthority> authorities) {
        super(userVO.getUserId(), userVO.getUserPW(), true, true, true, true, authorities);

    }

}
