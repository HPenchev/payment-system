package com.payment.paymentsystem.data.models;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{
    public Admin(String name, String email) {
        super(name, email);
    }
}
