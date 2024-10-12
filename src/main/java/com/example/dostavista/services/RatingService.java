package com.example.dostavista.services;

import com.example.dostavista.dtos.ratingsDtos.AddRatingDto;
import com.example.dostavista.dtos.ratingsDtos.AllRatingsDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingService {
    void addFeedback(AddRatingDto addRatingDto);
    Optional<AllRatingsDto> getRating(UUID id);
    List<AllRatingsDto> allFeedbacks();
    List<AllRatingsDto> getRatingsByCustomerId(UUID customerId);
    List<AllRatingsDto> getRatingsByCourierId(UUID courierId);
}