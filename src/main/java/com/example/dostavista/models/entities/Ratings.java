package com.example.dostavista.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Ratings extends BaseEntity {
    private Users customer;
    private Users courier;
    private int rating;
    private String description;
    private LocalDateTime timeCreation;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Users getCustomer() {
        return customer;
    }
    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "courier_id")
    public Users getCourier() {
        return courier;
    }
    public void setCourier(Users courier) {
        this.courier = courier;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(LocalDateTime timeCreation) {
        this.timeCreation = timeCreation;
    }
}
