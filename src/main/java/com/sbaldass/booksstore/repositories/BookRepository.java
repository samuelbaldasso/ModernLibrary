package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
