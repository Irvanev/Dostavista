package com.example.dostavista.dtos.ordersDtos;

import com.example.dostavista.models.enums.OrderStatusEnum;
import com.example.dostavista.models.enums.OrderTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public class AddOrderDto {
    private UUID customerId;
    private int price;
    private String pickupAddress;
    private String deliveryAddress;
    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;
    private String description;
    private String weight;
    private OrderTypeEnum type;

    public AddOrderDto(UUID customerId, OrderTypeEnum type) {
        this.customerId = customerId;
        this.type = type;
    }

    @Schema(description = "ID", example = "je82-ne-83bfjhw-nefe82")
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    @Schema(description = "Price", example = "17392")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Schema(description = "PickupAddress", example = "Москва")
    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    @Schema(description = "DeliveryAddress", example = "Москва")
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Schema(description = "PickupTime", example = "20.08.2024")
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

    public OrderTypeEnum getType() {
        return type;
    }

    public void setType(OrderTypeEnum type) {
        this.type = type;
    }
}
