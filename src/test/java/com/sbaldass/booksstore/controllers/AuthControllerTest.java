package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.config.JwtToken;
import com.sbaldass.booksstore.config.JwtUtils;
import com.sbaldass.booksstore.dtos.UserDTO;
import com.sbaldass.booksstore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        UserDTO userDTO = new UserDTO("samuel@gmail.com", "12345");
        Authentication authentication = mock(Authentication.class);

        User user = new User();
        user.setEmail(userDTO.getEmail());

        String email = authentication.getName();
        String token = jwtUtil.createToken(user);
        JwtToken jwtToken = new JwtToken(email, token);

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())))
                .thenReturn(authentication);
        when(jwtUtil.createToken(user)).thenReturn(token);

        ResponseEntity<JwtToken> response = authController.login(userDTO);

        assertEquals(ResponseEntity.ok(jwtToken), response);
        verify(jwtUtil, times(1)).createToken(user);
    }
}
