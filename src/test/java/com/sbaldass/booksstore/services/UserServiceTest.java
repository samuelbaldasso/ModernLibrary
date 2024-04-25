package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.UserDTO;
import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.RoleRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUserWithRoleFound() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAdmin(true);

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(role));

        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.saveUser(userDto);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
        assertTrue(result.getRoles().contains(role));
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testSaveUserWithRoleNotFound() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAdmin(true);

        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());

        try {
            userService.saveUser(userDto);
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertInstanceOf(Exception.class, e);
            verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
            verify(userRepository, never()).save(any(User.class));
        }
    }
    @Test
    public void testAlterUser() {
        Long id = 1L;
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testUser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setAdmin(true);

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));

        User existingUser = new User();
        existingUser.setId(id);
        existingUser.setUsername("existingUser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword(passwordEncoder.encode("existingPassword"));
        existingUser.setRoles(new ArrayList<>(Collections.singletonList(role)));

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));

        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("newEncodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.alterUser(userDto, id);

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("newEncodedPassword", result.getPassword());
        assertTrue(result.getRoles().contains(role));
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(any(User.class));
    }
    @Test
    public void testFindUserById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserById(id);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> result = userService.findAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = 1L;

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}