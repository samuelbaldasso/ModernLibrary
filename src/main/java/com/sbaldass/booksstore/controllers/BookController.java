package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.dtos.BookDTO;
import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book addBook(@RequestBody BookDTO book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO book) {
        return bookService.update(book, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void delete() {
        bookService.delete();
    }

    @PostMapping("/{bookId}/borrow/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        Book borrowedBook = bookService.borrowBook(bookId, userId);
        if (borrowedBook != null) {
            return ResponseEntity.ok(borrowedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}