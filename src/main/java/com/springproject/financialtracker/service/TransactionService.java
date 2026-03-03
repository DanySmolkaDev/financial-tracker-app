package com.springproject.financialtracker.service;

import com.springproject.financialtracker.model.Transaction;
import com.springproject.financialtracker.model.User;
import com.springproject.financialtracker.repository.TransactionRepository;
import com.springproject.financialtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final UserRepository userRepo;

    public TransactionService(TransactionRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public Transaction createTransaction(Transaction transaction) {
        if(transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        LocalDateTime date = LocalDateTime.now();
        transaction.setTransactionDate(date);

        Optional<User> id = userRepo.findById(transaction.getUser().getUserId());
        if(id.isPresent()) {
            transaction.setUser(id.get());
        }
        else{
            throw new IllegalArgumentException("User not found");
        }
        return repo.save(transaction);
    }
}
