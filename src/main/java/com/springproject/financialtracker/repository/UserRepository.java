package com.springproject.financialtracker.repository;

import com.springproject.financialtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
