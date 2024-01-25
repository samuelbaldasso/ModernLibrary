package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.dtos.BookDTO;
import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> findAll() throws Exception {
        return bookService.findAll();
    }

    @PostMapping
    public BookDTO save(@RequestBody BookDTO book) throws Exception {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Optional<BookDTO> findOne(@PathVariable Long id) throws Exception {
        return bookService.findById(id);
    }

    @PutMapping("/{id}")
    public Optional<BookDTO> update(@RequestBody Book newBook, @PathVariable Long id) throws Exception {
      return bookService.findById(id).map(book -> {
        book.setTitle(newBook.getTitle());
        book.setAuthor(newBook.getAuthor());
        book.setYearPublished(newBook.getYearPublished());
        book.setAvailable(newBook.isAvailable());
        try {
          return bookService.saveBook(book);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      });
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) throws Exception {
        bookService.delete(id);
    }
}
