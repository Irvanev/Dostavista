package com.example.dostavista.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Users extends BaseEntity {
    private List<Orders> courierOrders;
    private List<Orders> customerOrders;
    private List<Ratings> customer;
    private List<Ratings> courier;
    private String email;
    private String numberPhone;
    private String name;
    private String passport;
    private LocalDateTime timeCreation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier", cascade = CascadeType.REMOVE)
    public List<Orders> getCourierOrders() {
        return courierOrders;
    }
    public void setCourierOrders(List<Orders> courierOrders) {
        this.courierOrders = courierOrders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    public List<Orders> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Orders> customerOrders) {
        this.customerOrders = customerOrders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.REMOVE)
    public List<Ratings> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Ratings> customer) {
        this.customer = customer;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier", cascade = CascadeType.REMOVE)
    public List<Ratings> getCourier() {
        return courier;
    }

    public void setCourier(List<Ratings> courier) {
        this.courier = courier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDateTime getTimeCreation() {
        return timeCreation;
    }
    public void setTimeCreation(LocalDateTime timeCreation) {
        this.timeCreation = timeCreation;
    }
}
