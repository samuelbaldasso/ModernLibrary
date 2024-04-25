package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userService;

    @Test
    public void testLoadByUsername() {
        String email = "samuel@gmail.com";

        User user = new User();

        user.setEmail(email);

        user.setPassword("password");

        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));

        when(userRepository.findByEmail(email)).thenReturn(user);

        UserDetails result = userService.loadUserByUsername(email);

        assertEquals(userDetails, result);
        verify(userRepository, times(1)).findByEmail(email);

    }
}
