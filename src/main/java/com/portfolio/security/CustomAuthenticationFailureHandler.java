package com.portfolio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.fxml.PropertyNotFoundException;
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

        if (e instanceof BadCredentialsException) {

            failureDTO.setMessage("비밀번호를 확인해주세요.");

        } else if (e instanceof InternalAuthenticationServiceException) {

            failureDTO.setMessage("로그인에 실패했습니다.");
            
        } else if (e instanceof UsernameNotFoundException) {

            failureDTO.setMessage("아이디를 찾을 수 없습니다.");
        }

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonString = objectMapper.writeValueAsString(failureDTO);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().write(jsonString);

    }
}
