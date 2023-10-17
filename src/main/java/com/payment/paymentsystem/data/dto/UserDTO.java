package com.payment.paymentsystem.data.dto;

import com.payment.paymentsystem.data.MerchantStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class UserDTO {
    public UserDTO(String name, String email, String description, MerchantStatus status, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.status = status;
        this.userRole = userRole;
    }

    private UUID id;

    private String name;
    private String email;

    private String description;
    private MerchantStatus status;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", userRole=" + userRole +
                ", totalTransactionAmount=" + totalTransactionAmount +
                '}';
    }
}
