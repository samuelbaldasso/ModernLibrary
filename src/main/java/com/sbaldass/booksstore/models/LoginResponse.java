package com.sbaldass.booksstore.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
