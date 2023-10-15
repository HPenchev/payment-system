package com.payment.paymentsystem.data.repos;

import com.payment.paymentsystem.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
