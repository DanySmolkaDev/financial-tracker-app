package com.springproject.financialtracker.controller;

import com.springproject.financialtracker.dto.DashboardSummary;
import com.springproject.financialtracker.model.Transaction;
import com.springproject.financialtracker.model.User;
import com.springproject.financialtracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController {
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/transactions")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.createTransaction(transaction);

        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/api/transactions/dashboard/{userId}")
    public ResponseEntity<DashboardSummary> getSummary(@PathVariable Long userId) {
        User user = new User();
        user.setUserId(userId);

        BigDecimal monthlyCashFlow = transactionService.calculateMonthlyTransactions(user);
        return new ResponseEntity<>(new DashboardSummary(monthlyCashFlow), HttpStatus.OK);
    }
}
