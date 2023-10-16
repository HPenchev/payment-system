package com.payment.paymentsystem.data.dto;

import com.payment.paymentsystem.data.MerchantStatus;

import java.math.BigDecimal;

public class UserDTO {
    public UserDTO(String name, String email, String description, MerchantStatus status, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.status = status;
        this.userRole = userRole;
    }

    private final String name;
    private final String email;

    private final String description;
    private final MerchantStatus status;
    private final UserRole userRole;

    private BigDecimal totalTransactionAmount;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public BigDecimal getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(BigDecimal totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userRole=" + userRole +
                ", totalTransactionAmount=" + totalTransactionAmount +
                '}';
    }
}
