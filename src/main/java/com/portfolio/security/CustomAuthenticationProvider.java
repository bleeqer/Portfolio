package com.portfolio.security;

import com.portfolio.mapper.UserMapper;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

//        if (!matchPassword(password, user.getPassword())) {
//            throw new BadCredentialsException(userId);
//        }

        // 임시 로그인
        if (!Objects.equals(password, "458512")) {
            throw new BadCredentialsException(userId);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getAuthority()));

        return new UsernamePasswordAuthenticationToken(user, user, roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private boolean matchPassword(String loginPwd, String password) {
        return passwordEncoder.matches(loginPwd, password);
    }
}
