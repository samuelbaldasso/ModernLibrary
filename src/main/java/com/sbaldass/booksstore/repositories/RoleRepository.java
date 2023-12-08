package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
