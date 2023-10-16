package com.payment.paymentsystem.data.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class AuthoriseTransactionDTO extends TransactionDTO {
    public AuthoriseTransactionDTO(BigDecimal amount, String customerEmail, String customerPhone) {
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    private UUID id;

    @NotNull
    @Min(value = 0)
    private BigDecimal amount;

    @NotEmpty
    @NotNull
    private String customerEmail;

    @NotEmpty
    @NotNull
    private String customerPhone;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }


}
