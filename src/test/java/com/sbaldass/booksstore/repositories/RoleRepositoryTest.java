package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleRepositoryTest {

        @Mock
        private RoleRepository roleRepository;

        @Test
        public void testFindByName() {
            String name = "ROLE";
            Role role = new Role();
            Optional<Role> roleOptional = Optional.of(role);
            role.setName(name);
            when(roleRepository.findByName(name)).thenReturn(roleOptional);
            Optional<Role> result = roleRepository.findByName(name);
            assertEquals(roleOptional, result);
            verify(roleRepository, times(1)).findByName(name);
    }

}
