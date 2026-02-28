package com.springproject.financialtracker.repository;

import com.springproject.financialtracker.model.Transaction;
import com.springproject.financialtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByUser(User user);
}
