package com.payment.paymentsystem.data.dto;

public enum UserRole {
    ADMIN, MERCHANT;

    public static UserRole fromString(String text) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(text)) {
                return role;
            }
        }

        return null;
    }
}
