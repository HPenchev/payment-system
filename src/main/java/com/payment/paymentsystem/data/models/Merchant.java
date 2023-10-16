package com.payment.paymentsystem.data.models;

import com.payment.paymentsystem.data.MerchantStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Merchant extends User {
    @Column
    private String description;
    @Column
    private MerchantStatus status;

    @OneToMany(mappedBy="merchant")
    private Set<ChargeTransaction> transactions;

    public Merchant(){
        super();
    }
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

    public Set<ChargeTransaction> getTransactions() {
        return transactions;
    }
}
