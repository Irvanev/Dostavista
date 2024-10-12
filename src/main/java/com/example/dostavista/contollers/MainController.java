package com.example.dostavista.contollers;

import com.example.dostavista.models.entities.MainClass;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping
    public ResponseEntity<?> getRoot() {
        Link usersLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).allUsers())
                .withRel("users")
                .withTitle("Запросы для пользвателей");

        Link orersLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).allOrders())
                .withRel("orders")
                .withTitle("Запросы для заказов");

        Link ratingsLink = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(RatingController.class).allRatings())
                .withRel("feedbacks")
                .withTitle("Запросы для отзывов");

        return ResponseEntity.ok(
                new MainClass(usersLink, orersLink, ratingsLink)
        );
    }
}
