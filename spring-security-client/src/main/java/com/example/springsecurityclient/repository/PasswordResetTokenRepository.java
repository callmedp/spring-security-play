package com.example.springsecurityclient.repository;

import com.example.springsecurityclient.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    @Query
    PasswordResetToken findByToken(String token);
}
