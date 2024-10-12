package com.example.dostavista.dtos.ratingsDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.UUID;

public class AllRatingsDto extends RepresentationModel<AllRatingsDto> {
    private UUID id;
    private UUID customerId;
    private UUID courierId;
    private int rating;
    private String description;
    private LocalDateTime timeCreation;

    protected AllRatingsDto() {}

    public AllRatingsDto(UUID id, UUID customerId, UUID courierId, int rating, String description, LocalDateTime timeCreation) {
        this.id = id;
        this.customerId = customerId;
        this.courierId = courierId;
        this.rating = rating;
        this.description = description;
        this.timeCreation = timeCreation;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getCourierId() {
        return courierId;
    }

    public void setCourierId(UUID courierId) {
        this.courierId = courierId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeCreation() {
        return timeCreation;
    }

    public void setTimeCreation(LocalDateTime timeCreation) {
        this.timeCreation = timeCreation;
    }
}
