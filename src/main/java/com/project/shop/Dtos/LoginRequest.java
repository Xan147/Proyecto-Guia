package com.project.shop.Dtos;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
