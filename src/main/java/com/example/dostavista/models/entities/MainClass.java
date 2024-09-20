package com.example.dostavista.models.entities;

import org.springframework.hateoas.Link;

public class MainClass {
    private final Link users;
    private final Link orders;

    public MainClass(Link users, Link orders) {
        this.users = users;
        this.orders = orders;
    }

    public Link getUsers() {
        return users;
    }

    public Link getOrders() {
        return orders;
    }
}