package com.payment.paymentsystem.data.models;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "users")
public abstract class User {
    public User(){}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="name", nullable = false)

    private String name;
    @Column(name="email", nullable = false, unique = true)
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
