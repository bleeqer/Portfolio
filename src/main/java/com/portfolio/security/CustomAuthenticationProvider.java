package com.portfolio.security;

import com.portfolio.mapper.UserMapper;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider() {
        System.out.println("시발");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("아니씨빨왜");
        String userId = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

        System.out.println("Auth 유저네임: " + user.getUsername());
        System.out.println("Auth 비밀번호: " + user.getPassword());

        if (!matchPassword(password, user.getPassword())) {
            throw new BadCredentialsException(userId);
        }

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(user.getAuthority()));



        return new UsernamePasswordAuthenticationToken(userId, password, roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }


    private boolean matchPassword(String loginPwd, String password) {
        System.out.println(passwordEncoder.matches(loginPwd, password));
        return passwordEncoder.matches(loginPwd, password);
    }
}
