package com.portfolio.exception;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {

        System.out.println(e.getClass().getSimpleName());
        System.out.println(e.getMessage());

        if (e instanceof MyBatisSystemException || e instanceof SQLException || e instanceof IOException || e instanceof NullPointerException) {

            model.addAttribute("errorCode", 500);

        } else if (e instanceof MethodArgumentTypeMismatchException || e instanceof IllegalArgumentException) {

            model.addAttribute("errorCode", 400);

        }

        return "common/errorPage";
    }


}
