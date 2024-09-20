package com.example.dostavista.services.impl;

import com.example.dostavista.dtos.ordersDtos.AddOrderDto;
import com.example.dostavista.dtos.ordersDtos.AllOrdersDto;
import com.example.dostavista.dtos.ordersDtos.TakeOrderDto;
import com.example.dostavista.models.entities.Orders;
import com.example.dostavista.models.enums.OrderStatusEnum;
import com.example.dostavista.repositories.OrderRepository;
import com.example.dostavista.repositories.UserRepository;
import com.example.dostavista.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOrder(AddOrderDto addOrderDto) {
        Orders order = modelMapper.map(addOrderDto, Orders.class);
        order.setCustomer(userRepository.findById(addOrderDto.getOrderId()).orElseThrow());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void takeOrder(TakeOrderDto takeOrderDto, UUID orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        order.setCourier(userRepository.findById(takeOrderDto.getCourierId()).orElseThrow());
        order.setStatus(OrderStatusEnum.IN_PROGRESS);
        orderRepository.saveAndFlush(order);
    }

//    @Override
//    public List<AllOrdersDto> getCustomersOrders(UUID userId) {
//        return orderRepository.findByCustomerId(userId).stream().map(order -> modelMapper.map(order, AllOrdersDto.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public Optional<AllOrdersDto> getOrder(UUID orderId) {
        return orderRepository.findById(orderId).map(orders -> modelMapper.map(orders, AllOrdersDto.class));
    }

    @Override
    public List<AllOrdersDto> allOrders() {
        return orderRepository.findAll().stream().map(order -> modelMapper.map(order, AllOrdersDto.class))
                .collect(Collectors.toList());
    }
}
