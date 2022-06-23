package com.portfolio.error;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class errorAdvice {

    @ExceptionHandler(Exception.class)
    public String errorException(Model model, Exception e) {

        System.out.println(e.getClass().getSimpleName());
        System.out.println(e.getMessage());

        if (e instanceof MyBatisSystemException) {

            model.addAttribute("errorCode", 500);

        } else if (e instanceof MethodArgumentTypeMismatchException || e instanceof NullPointerException) {

            model.addAttribute("errorCode", 400);

        }


        return "common/errorPage";
    }


}
