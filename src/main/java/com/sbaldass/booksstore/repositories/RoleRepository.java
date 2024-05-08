package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Role;
import com.sbaldass.booksstore.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
