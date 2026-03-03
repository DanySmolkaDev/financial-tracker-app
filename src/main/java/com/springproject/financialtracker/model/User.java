package com.springproject.financialtracker.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    Role role;

    BigDecimal budget;

    public User(){}

    public User(Long userId, String username, String password, Role role, BigDecimal balance, BigDecimal budget) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.budget = budget;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {this.userId = userId;}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public BigDecimal getBudget() {
        return budget;
    }
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
