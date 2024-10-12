package com.example.dostavista.models.entities;

import com.example.dostavista.models.enums.OrderStatusEnum;
import com.example.dostavista.models.enums.OrderTypeEnum;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Orders extends BaseEntity {
    private Users customer;
    private Users courier;
    private int price;
    private String pickupAddress;
    private String deliveryAddress;
    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;
    private OrderStatusEnum status;
    private OrderTypeEnum type;
    private String description;
    private String weight;
    private LocalDateTime created;

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

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Enumerated(EnumType.STRING)
    public OrderStatusEnum getStatus() {
        return status;
    }
    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    public OrderTypeEnum getType() {
        return type;
    }
    public void setType(OrderTypeEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
