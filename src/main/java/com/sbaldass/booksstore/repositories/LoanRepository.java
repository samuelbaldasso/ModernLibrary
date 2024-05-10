package com.sbaldass.booksstore.repositories;

import com.sbaldass.booksstore.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
