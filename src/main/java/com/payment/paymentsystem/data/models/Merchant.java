package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.MerchantStatus;
import jakarta.persistence.Entity;

@Entity
public class Merchant extends User{
    private String description;
    private MerchantStatus status;

    public Merchant(String name, String email, String description, MerchantStatus status) {
        super(name, email);
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MerchantStatus getStatus() {
        return status;
    }

    public void setStatus(MerchantStatus status) {
        this.status = status;
    }
}
