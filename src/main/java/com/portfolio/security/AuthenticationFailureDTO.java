package com.portfolio.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationFailureDTO {

    private boolean success;
    private String message;

}
