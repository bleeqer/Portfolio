package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserVO {

    private String email;
    private String name;
    private String password;
    private String photo;
    private String authority;
    private String occupation;


}


