package com.sbaldass.booksstore.services;

import com.sbaldass.booksstore.dtos.LoanHistoryDTO;
import com.sbaldass.booksstore.models.Loan;
import com.sbaldass.booksstore.models.LoanHistory;
import com.sbaldass.booksstore.repositories.LoanHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanHistoryService {

    @Autowired
    private LoanHistoryRepository loanHistoryRepository;

    public List<LoanHistoryDTO> findLoanHistoryByUserId(Long userId) throws Exception{
        List<LoanHistory> histories = loanHistoryRepository.findByUserId(userId);
        return histories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public List<LoanHistoryDTO> findLoanHistoryByBookId(Long bookId) throws Exception{
        List<LoanHistory> histories = loanHistoryRepository.findByBookId(bookId);
        return histories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LoanHistoryDTO convertToDTO(LoanHistory loanHistory) {
        LoanHistoryDTO loanHistoryDTO = new LoanHistoryDTO();
        loanHistoryDTO.setId(loanHistory.getId());
        loanHistoryDTO.setLoanId(loanHistory.getLoanId());
        loanHistoryDTO.setBookId(loanHistory.getBookId());
        loanHistoryDTO.setUserId(loanHistory.getUserId());
        loanHistoryDTO.setStartDate(loanHistory.getStartDate());
        loanHistoryDTO.setEndDate(loanHistory.getEndDate());
        loanHistoryDTO.setStatus(loanHistory.getStatus());
        return loanHistoryDTO;
    }
}
