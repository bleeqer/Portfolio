package com.portfolio.security;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        Map<String, String> map = new HashMap<>();
        map.put("result", "fail");

        if (e instanceof BadCredentialsException) {
            map.put("msg", "아이디 또는 비밀번호를 확인해주세요.");

        } else if (e instanceof InternalAuthenticationServiceException) {
            map.put("msg", "아이디 또는 비밀번호를 확인해주세요.");
        }

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        if (jsonConverter.canWrite(map.getClass(), MediaType.APPLICATION_JSON)) {
            jsonConverter.write(map, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(httpServletResponse));
        }

    }
}
