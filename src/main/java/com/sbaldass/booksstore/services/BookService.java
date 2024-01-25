package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.BookDTO;
import com.sbaldass.booksstore.models.Book;
import com.sbaldass.booksstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAll() throws Exception{
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO saveBook(BookDTO bookDTO) throws Exception{
        Book book = convertToEntity(bookDTO);
        if(book.getId() == null) {
            throw new Exception("Não é possível salvar um livro com um ID.");
        }
        return convertToDTO(bookRepository.save(book));
    }

    public Optional<BookDTO> findById(Long id) throws Exception{
      if(id == null) {
        throw new Exception("Não é possível salvar um livro com um ID.");
    }
        return bookRepository.findById(id)
                .map(this::convertToDTO);
    }

    public void delete(Long id) throws Exception{
      if(id == null) {
        throw new Exception("Não é possível salvar um livro com um ID.");
    }
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDTO(Book book) {
        try {
            return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getYearPublished(), book.isAvailable());
        } catch (Exception e) {
            throw new RuntimeException("Conversão mal sucedida.");
        }
    }

    private Book convertToEntity(BookDTO bookDTO) {
        try {
            return new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getYearPublished(), bookDTO.isAvailable());
        } catch (Exception e) {
            throw new RuntimeException("Conversão mal sucedida.");
        }
    }
}

