package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.LoanDTO;
import com.sbaldass.booksstore.models.*;
import com.sbaldass.booksstore.repositories.BookRepository;
import com.sbaldass.booksstore.repositories.LoanHistoryRepository;
import com.sbaldass.booksstore.repositories.LoanRepository;
import com.sbaldass.booksstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanHistoryRepository loanHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public LoanDTO loanBook(LoanDTO loanDTO) throws Exception {
        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (book.isAvailable()) {
            Loan loan = convertToEntity(loanDTO);

            loan.setBook(book);
            loan.setStartDate(LocalDate.now());
            loan.setStatus(LoanStatus.EMPRESTADO);

            book.setAvailable(false);
            bookRepository.save(book);

            Loan savedLoan = loanRepository.save(loan);
            return convertToDTO(savedLoan);
        } else {
            throw new RuntimeException("Livro não está disponível para empréstimo");
        }
    }
    @Transactional
    public LoanDTO returnBook(Long loanId) throws Exception {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        loan.setEndDate(LocalDate.now());
        loan.setStatus(LoanStatus.DEVOLVIDO);

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        LoanHistory history = new LoanHistory();
        history.setLoanId(loan.getId());
        history.setBookId(book.getId());
        history.setUserId(loan.getUser().getId());
        history.setStartDate(loan.getStartDate());
        history.setEndDate(LocalDate.now());
        history.setStatus(loan.getStatus());
        loanHistoryRepository.save(history);

        Loan updatedLoan = loanRepository.save(loan);
        return convertToDTO(updatedLoan);
    }

    public List<LoanDTO> getAllLoans(){
        return loanRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    private Loan convertToEntity(LoanDTO loanDTO) {
        Loan loan = new Loan();

        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        User user = userRepository.findById(loanDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        loan.setBook(book);
        loan.setUser(user);
        loan.setStartDate(loanDTO.getStartDate());
        loan.setEndDate(loanDTO.getEndDate());
        loan.setStatus(loanDTO.getStatus());

        return loan;
    }

    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setBookId(loan.getBook().getId());
        loanDTO.setUserId(loan.getUser().getId());
        loanDTO.setStartDate(loan.getStartDate());
        loanDTO.setEndDate(loan.getEndDate());
        loanDTO.setStatus(loan.getStatus());

        return loanDTO;
    }
}
