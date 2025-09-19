package com.example.CouDDa.domain;

import java.time.LocalDateTime;


public class Seller {
    private long id;
    private String name;
    private String contactEmail;
    private LocalDateTime createdAt;

    public Seller(long id, String name, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.createdAt = LocalDateTime.now();
    }

    // getters, setters, toString() 등
    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}