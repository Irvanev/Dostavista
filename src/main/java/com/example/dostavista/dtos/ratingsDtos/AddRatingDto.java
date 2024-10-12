package com.example.dostavista.dtos.ratingsDtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class AddRatingDto {
    private UUID customerId;
    private UUID courierId;
    private int rating;
    private String description;

    protected AddRatingDto() {}

    @Schema(description = "customerId", example = "je82-ne-83bfjhw-nefe82")
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    @Schema(description = "courierId", example = "je82-ne-83bfjhw-nefe82")
    public UUID getCourierId() {
        return courierId;
    }

    public void setCourierId(UUID courierId) {
        this.courierId = courierId;
    }

    @Schema(description = "rating", example = "5")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Schema(description = "description", example = "Быстро и качественно!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
