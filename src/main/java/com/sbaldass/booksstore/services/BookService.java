package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.BookDTO;
import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.models.User;
import com.sbaldass.booksstore.repositories.BookRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado."));
    }

    public Book save(BookDTO book) {
        Book book1 = new Book();
        book1.setBorrowed(book.isBorrowed());
        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());
        book1.setYearLaunched(book.getYearLaunched());

        return bookRepository.save(book1);
    }

    public Book update(BookDTO book, Long id) {
        Book book1 = findById(id);
        book1.setBorrowed(book.isBorrowed());
        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());
        book1.setYearLaunched(book.getYearLaunched());

        return bookRepository.save(book1);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public void delete() {
        bookRepository.deleteAll();
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (book != null && !book.isBorrowed() && user != null) {
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            book.setBorrowedAt(LocalDate.now());
            return bookRepository.save(book);
        }
        return null;
    }

    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            book.setReturnedAt(LocalDate.now());
            return bookRepository.save(book);
        }
        return null;
    }
}