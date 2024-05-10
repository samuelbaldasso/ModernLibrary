package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
