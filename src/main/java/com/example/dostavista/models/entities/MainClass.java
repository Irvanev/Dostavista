package com.example.dostavista.models.entities;

import org.springframework.hateoas.Link;

public class MainClass {
    private final Link users;
    private final Link orders;
    private final Link ratings;

    public MainClass(Link users, Link orders, Link ratings) {
        this.users = users;
        this.orders = orders;
        this.ratings = ratings;
    }

    public Link getRatings() {
        return ratings;
    }

    public Link getUsers() {
        return users;
    }

    public Link getOrders() {
        return orders;
    }
}