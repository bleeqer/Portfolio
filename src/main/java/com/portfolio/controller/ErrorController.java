package com.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("{errorCode}")
    public String error(@PathVariable int errorCode, Model model) {

        if (errorCode == 400) {
            model.addAttribute("msg", "잘못된 요청입니다.");
        } else if (errorCode == 403) {
            model.addAttribute("msg", "접근할 수 없는 페이지입니다.");
        } else if (errorCode == 404) {
            model.addAttribute("msg", "페이지를 찾을 수 없습니다.");
        } else if (errorCode == 500) {
            model.addAttribute("msg", "서버 내부 오류입니다. 관리자에게 문의하세요.");
        } else if (errorCode == 503) {
            model.addAttribute("msg", "서비스를 이용할 수 없습니다. 관리자에게 문의하세요.");
        }

        return "common/errorPage";
    }

}
