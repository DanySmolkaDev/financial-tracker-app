package com.springproject.financialtracker.service;

import com.springproject.financialtracker.model.Transaction;
import com.springproject.financialtracker.model.TransactionType;
import com.springproject.financialtracker.model.User;
import com.springproject.financialtracker.repository.TransactionRepository;
import com.springproject.financialtracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
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

    public BigDecimal calculateMonthlyTransactions(User user) {
        BigDecimal monthlyCashFlow = BigDecimal.ZERO;

        //Generate start and end dates of the current month
        LocalDateTime from = LocalDateTime.now()
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withMinute(0).withSecond(0);

        LocalDateTime to = LocalDateTime.now()
                .with(TemporalAdjusters.lastDayOfMonth())
                .withHour(23).withMinute(59).withSecond(59);

        List<Transaction> transactions = repo.findByUser_UserIdAndTransactionDateBetween(user.getUserId(), from, to);

        for(Transaction transaction : transactions) {
            if(transaction.getTransactionType() == TransactionType.INCOME){
                monthlyCashFlow = monthlyCashFlow.add(transaction.getAmount());
            }
            else{
                monthlyCashFlow = monthlyCashFlow.subtract(transaction.getAmount());
            }
        }
        return monthlyCashFlow;
    }
}
