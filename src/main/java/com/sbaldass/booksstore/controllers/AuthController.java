package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.dtos.LoginUserDTO;
import com.sbaldass.booksstore.dtos.RegisterUserDTO;
import com.sbaldass.booksstore.models.LoginResponse;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.services.AuthService;
import com.sbaldass.booksstore.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private JwtService jwtService;

    private AuthService authenticationService;

    @Autowired
    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}

