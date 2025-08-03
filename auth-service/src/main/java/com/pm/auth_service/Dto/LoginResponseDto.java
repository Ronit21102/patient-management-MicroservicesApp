package com.pm.auth_service.Dto;

public class LoginResponseDto {

    private final String token;


    public LoginResponseDto(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

}
