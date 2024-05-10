package com.sbaldass.booksstore.controllers;

import com.sbaldass.booksstore.dtos.LoanHistoryDTO;
import com.sbaldass.booksstore.services.LoanHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanHistory")
public class LoanHistoryController {

    @Autowired
    private LoanHistoryService loanHistoryService;

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanHistoryDTO>> getLoanHistoryByBook(@PathVariable Long bookId) throws Exception {
        List<LoanHistoryDTO> history = loanHistoryService.findLoanHistoryByBookId(bookId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanHistoryDTO>> getLoanHistoryByUser(@PathVariable Long userId) throws Exception {
        List<LoanHistoryDTO> history = loanHistoryService.findLoanHistoryByUserId(userId);
        return ResponseEntity.ok(history);
    }
}
