package com.payment.paymentsystem.data.repos;

import com.payment.paymentsystem.data.models.Merchant;
import com.payment.paymentsystem.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public Optional<User> findByEmail(String email);
}
