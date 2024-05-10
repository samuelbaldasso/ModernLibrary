package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.dtos.UserDTO;
import com.sbaldass.booksstore.config.JwtToken;
import com.sbaldass.booksstore.config.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody UserDTO userDTO) throws BadCredentialsException {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        String email = authentication.getName();
        User user = new User();
        String token = jwtUtil.createToken(user);
        JwtToken loginRes = new JwtToken(email, token);

        return ResponseEntity.ok(loginRes);
    }
}