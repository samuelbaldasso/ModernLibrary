package com.sbaldass.booksstore.controllers;

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
    public List<Book> findAll(){
        return bookService.findAll();
    }

    @PostMapping
    public Book save(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable Long id){
        return bookService.findById(id);
    }

    @PutMapping("/{id}")
    public Optional<Book> update(@RequestBody Book newBook, @PathVariable Long id){
        return bookService.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setYearPublished(newBook.getYearPublished());
            return bookService.saveBook(book);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }
}
