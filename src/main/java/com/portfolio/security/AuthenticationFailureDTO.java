package com.portfolio.security;



public class AuthenticationFailureDTO {

    private String attrName;
    private String message;
    private Object data;


    public AuthenticationFailureDTO(String errorMsg, String s) {
        this.attrName=errorMsg;
        this.message=s;
    }
}
