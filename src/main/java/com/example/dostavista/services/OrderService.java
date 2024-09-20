package com.example.dostavista.services;

import com.example.dostavista.dtos.ordersDtos.AddOrderDto;
import com.example.dostavista.dtos.ordersDtos.AllOrdersDto;
import com.example.dostavista.dtos.ordersDtos.TakeOrderDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<AllOrdersDto> getOrder(UUID orderId);
    void addOrder(AddOrderDto addOrderDto);
    void takeOrder(TakeOrderDto takeOrderDto, UUID orderId);
    //List<AllOrdersDto> getCustomersOrders(UUID userId);
    List<AllOrdersDto> allOrders();
}
