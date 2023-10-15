package com.payment.paymentsystem;

import com.payment.paymentsystem.data.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static com.payment.paymentsystem.data.MerchantStatus.ACTIVE;
import static com.payment.paymentsystem.data.dto.UserRole.ADMIN;
import static com.payment.paymentsystem.data.dto.UserRole.MERCHANT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVHelperTest {
    @Test
    public void testCsvToUsers() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("users.csv")) {
            Collection<UserDTO> users = CSVHelper.csvToUsers(is);
            assertEquals(5, users.size());

            UserDTO admin = users.stream().filter(u -> u.getName().equals(("test1"))).findAny().get();
            assertEquals("test1@test.ts", admin.getEmail());
            assertEquals(ADMIN, admin.getUserRole());

            UserDTO merchant = users.stream().filter(u -> u.getName().equals(("test3"))).findAny().get();
            assertEquals("test3@test.ts", merchant.getEmail());
            assertEquals(MERCHANT, merchant.getUserRole());
            assertEquals("testDescription 3", merchant.getDescription());
            assertEquals(ACTIVE, merchant.getStatus());
        }
    }

    @Test
    public void testCsvToUsersInvalid() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("invalid.csv")) {
            assertThrows(RuntimeException.class, () -> {
                CSVHelper.csvToUsers(is);
            });
        }

    }
}
