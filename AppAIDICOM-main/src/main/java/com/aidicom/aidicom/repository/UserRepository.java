package com.aidicom.aidicom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aidicom.aidicom.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
