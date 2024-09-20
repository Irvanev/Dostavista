package com.example.dostavista.dtos.ordersDtos;

import com.example.dostavista.models.enums.OrderStatusEnum;

import java.util.UUID;

public class TakeOrderDto {
    private OrderStatusEnum orderStatus;
    private UUID courierId;

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public void setCourierId(UUID courierId) {
        this.courierId = courierId;
    }
}
