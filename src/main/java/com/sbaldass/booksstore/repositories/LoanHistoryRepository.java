package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Loan;
import com.sbaldass.booksstore.models.LoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {
    List<LoanHistory> findByUserId(Long userId);
    List<LoanHistory> findByBookId(Long bookId);

}
