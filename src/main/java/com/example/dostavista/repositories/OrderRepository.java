package com.example.dostavista.repositories;

import com.example.dostavista.models.entities.Orders;
import com.example.dostavista.models.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByCustomerId(UUID customerId);
    List<Orders> findByCourierId(UUID courierId);
    List<Orders> findOrderByStatus(OrderStatusEnum status);
}
