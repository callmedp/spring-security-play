package com.example.springsecurityclient.repository;

import com.example.springsecurityclient.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query
    User findByEmail(String email);
}
