package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.data.models.Admin;
import com.payment.paymentsystem.data.models.Merchant;
import com.payment.paymentsystem.data.models.User;
import org.junit.jupiter.api.Test;


import static com.payment.paymentsystem.data.MerchantStatus.ACTIVE;
import static com.payment.paymentsystem.data.dto.UserRole.ADMIN;
import static com.payment.paymentsystem.data.dto.UserRole.MERCHANT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFactoryTest {
    @Test
    public void testCreateAdmin() {
        String name = "test";
        String email = "test@test.ts";
        UserDTO admin = new UserDTO(name, email, null, null, ADMIN);
        User result = new UserFactory().createUser(admin);

        assertEquals (Admin.class, result.getClass());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testCreateMerchant() {
        String name = "test";
        String email = "test@test.ts";
        String description = "testDescription";

        UserDTO merchant = new UserDTO(name, email, description, ACTIVE, MERCHANT);
        Merchant result = (Merchant) new UserFactory().createUser(merchant);

        assertEquals (Merchant.class, result.getClass());
        assertEquals(name, result.getName());
        assertEquals(email, result.getEmail());
        assertEquals(description, result.getDescription());
        assertEquals(ACTIVE, result.getStatus());
    }
}
