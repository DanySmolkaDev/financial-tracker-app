package com.springproject.financialtracker.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public Transaction() {}
    public Transaction(BigDecimal amount, LocalDateTime transactionDate, TransactionType transactionType, Category category, User user) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.category = category;
        this.user = user;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
