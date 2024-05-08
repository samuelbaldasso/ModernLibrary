package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.LoginUserDTO;
import com.sbaldass.booksstore.dtos.RegisterUserDTO;
import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.RoleName;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.RoleRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User signup(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setRole(optionalRole.get());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }


    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public User adminSignUp(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setRole(optionalRole.get());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

}
