package com.example.dostavista.contollers;

import com.example.dostavista.dtos.ordersDtos.AddOrderDto;
import com.example.dostavista.dtos.ordersDtos.AllOrdersDto;
import com.example.dostavista.dtos.ordersDtos.TakeOrderDto;
import com.example.dostavista.models.enums.OrderStatusEnum;
import com.example.dostavista.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Заказы", description = "Взаимодействие с заказами")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Добавление заказа", description = "Позволяет добавить заказ")
    @PostMapping
    public void addOrder(@RequestBody @Valid AddOrderDto addOrderDto) {
        orderService.addOrder(addOrderDto);

    }

    @GetMapping
    @Operation(summary = "Список всех заказов", description = "Позволяет вывести все заказы")
    public ResponseEntity<CollectionModel<AllOrdersDto>> allOrders() {
        List<AllOrdersDto> orders = orderService.allOrders();
        orders.forEach(order -> {
            order.add(linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel());
            order.add(linkTo(methodOn(OrderController.class).takeOrder(order.getId(), null)).withRel("takeOrder"));
        });
        Link allOrdersLink = linkTo(methodOn(OrderController.class).allOrders())
                .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(orders, allOrdersLink));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск заказа по ID", description = "Позволяет найти заказ по ID")
    public AllOrdersDto getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id)
                .map(order -> {
                    order.add(linkTo(methodOn(OrderController.class).getOrder(id))
                            .withSelfRel());
                    return order;
                })
                .orElse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление персональных данных пользователя", description = "Обновление персональных данных пользователя")
    public ResponseEntity<AllOrdersDto> takeOrder(@PathVariable UUID id, @RequestBody @Valid TakeOrderDto takeOrderDto) {
        orderService.takeOrder(takeOrderDto, id);

        AllOrdersDto takeOrder = orderService.getOrder(id)
                .map(order -> {
                    order.add(linkTo(methodOn(OrderController.class).getOrder(id))
                            .withSelfRel());
                    return order;
                })
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return ResponseEntity.ok(takeOrder);
    }

    @GetMapping("/{customerId}")
    public List<AllOrdersDto> getOrdersCustomerId(@PathVariable UUID customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    @GetMapping("/created")
    public List<AllOrdersDto> getCreatedOrders() {
        return orderService.allCreateOrders(OrderStatusEnum.CREATED);
    }

    @GetMapping("/created")
    public List<AllOrdersDto> getTakenOrders() {
        return orderService.allCreateOrders(OrderStatusEnum.TAKEN);
    }

    @GetMapping("/in_progress")
    public List<AllOrdersDto> getProgressOrders() {
        return orderService.allCreateOrders(OrderStatusEnum.IN_PROGRESS);
    }

    @GetMapping("/completed")
    public List<AllOrdersDto> getCompletedOrders() {
        return orderService.allCreateOrders(OrderStatusEnum.COMPLETED);
    }
}
