package com.payment.paymentsystem.services;

import com.payment.paymentsystem.data.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.payment.paymentsystem.data.MerchantStatus.ACTIVE;
import static com.payment.paymentsystem.data.dto.UserRole.ADMIN;
import static com.payment.paymentsystem.data.dto.UserRole.MERCHANT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserVaidatorTest {

    @Test
    public void testExtractValidUsers() {
        assertEquals(2, new UserValidator().extractValidUsers(Arrays.asList(
                new UserDTO("TestAdmin", "test@test.ts", null, null, ADMIN),
                new UserDTO("TestMerchant", "testmer@test.ts", "merchDescr", ACTIVE, MERCHANT),
                new UserDTO("", "testmerinv@test.ts", "merchDescr", ACTIVE, MERCHANT),
                new UserDTO("invalidName", "", "merchDescr", ACTIVE, MERCHANT),
                new UserDTO("invalidName", "", "merchDescr", ACTIVE, null)
        )).size());
    }
}
