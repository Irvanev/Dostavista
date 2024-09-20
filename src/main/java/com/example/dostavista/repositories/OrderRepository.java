package com.example.dostavista.repositories;

import com.example.dostavista.models.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    void findByCustomerId(UUID customerId);
    void findByCourierId(UUID orderId);
}
