package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }
}
