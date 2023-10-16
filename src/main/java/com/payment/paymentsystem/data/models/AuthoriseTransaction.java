package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.TransactionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class AuthoriseTransaction extends Transaction{
    public AuthoriseTransaction() {
        super();
    }

    public AuthoriseTransaction(BigDecimal amount,
                                String customerEmail,
                                String customerPhone,
                                TransactionStatus status) {
        super(status);
        this.amount = amount;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    @Column(name="amount")
    BigDecimal amount;

    @Column(name="customer_email")
    String customerEmail;

    @Column(name="customer_phone")
    String customerPhone;

    @OneToOne
    @JoinColumn(name = "charge_transaction_id", referencedColumnName = "id")
    ChargeTransaction chargeTransaction;

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public ChargeTransaction getChargeTransaction() {
        return chargeTransaction;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setChargeTransaction(ChargeTransaction chargeTransaction) {
        this.chargeTransaction = chargeTransaction;
    }
}
