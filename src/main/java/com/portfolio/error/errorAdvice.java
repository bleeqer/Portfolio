package com.portfolio.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class errorAdvice {

    @ExceptionHandler(Exception.class)
    public String errorException(Model model, Exception e) {

//        String ErrorType = e.getClass().getSimpleName();

//        if (ErrorType == "NoHandlerFoundException")
        System.out.println(e.getClass().getSimpleName());
        if (e instanceof NoHandlerFoundException) {
            model.addAttribute("message", "페이지를 찾을 수 없습니다.");
        }


        return "common/errorPage";
    }


}
