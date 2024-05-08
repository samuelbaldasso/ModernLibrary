package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.config.BCryptPasswordEncoder;
import com.sbaldass.booksstore.dtos.RegisterUserDTO;
import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.RoleName;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.RoleRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

    public User createAdministrator(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user
                .setFullName(input.getFullName());
        user
                .setEmail(input.getEmail());
        user
                .setPassword(passwordEncoder.passwordEncoder().encode(input.getPassword()));
        user
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }

}
