package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.dto.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;

import static com.payment.paymentsystem.data.dto.UserRole.ADMIN;
import static com.payment.paymentsystem.data.dto.UserRole.MERCHANT;

@Service
class UserValidator {
    Logger logger = LoggerFactory.getLogger(UserValidator.class);

    Collection<UserDTO> extractValidUsers(Collection<UserDTO> users) {
        Collection<UserDTO> validUsers = new HashSet<>();

        for (UserDTO user : users) {
            UserRole role = user.getUserRole();

            if (!StringUtils.hasText(user.getName()) || !StringUtils.hasText(user.getEmail())) {
                logger.error("Fields name and email are mandatory and user " + user + " will not be imported.");
            } else if (role != ADMIN && role != MERCHANT) {
                logger.error("Unknown user role " + user.getUserRole() + ". User " + user + " will not be imported.");
            } else {
                validUsers.add(user);
            }
        }

        return validUsers;
    }
}
