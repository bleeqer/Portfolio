package com.portfolio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        AuthenticationFailureDTO failureDTO = new AuthenticationFailureDTO();

        if (e instanceof BadCredentialsException || e instanceof InternalAuthenticationServiceException) {

            failureDTO.setSuccess(false);
            failureDTO.setMessage("아이디 또는 비밀번호를 확인해주세요.");

        } else if (e instanceof UsernameNotFoundException) {

            failureDTO.setSuccess(false);
            failureDTO.setMessage("존재하지 않는 아이디 입니다.");

        }

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonString = objectMapper.writeValueAsString(failureDTO);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(jsonString);


    }
}
