package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.dto.UserRole;
import com.payment.paymentsystem.data.models.Admin;
import com.payment.paymentsystem.data.models.Merchant;
import com.payment.paymentsystem.data.models.User;
import org.springframework.stereotype.Service;

@Service
class UserFactory {

    User createUser(UserDTO user) {
        String name = user.getName();
        String email = user.getEmail();
        UserRole role = user.getUserRole();

        switch (role) {
            case ADMIN -> {
                return new Admin(name, email);
            }

            case MERCHANT -> {
                return new Merchant(name, email, user.getDescription(), user.getStatus());
            }

            default -> throw new IllegalArgumentException("Unknown user role " + role);
        }
    }
}