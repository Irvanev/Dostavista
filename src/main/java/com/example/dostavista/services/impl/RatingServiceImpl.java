package com.example.dostavista.services.impl;

import com.example.dostavista.dtos.ratingsDtos.AddRatingDto;
import com.example.dostavista.dtos.ratingsDtos.AllRatingsDto;
import com.example.dostavista.models.entities.Ratings;
import com.example.dostavista.repositories.RatingRepository;
import com.example.dostavista.repositories.UserRepository;
import com.example.dostavista.services.RatingService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addFeedback(AddRatingDto addRatingDto) {
        if (addRatingDto.getCourierId().equals(addRatingDto.getCustomerId())) {
            throw new IllegalArgumentException("Courier and customer cannot be the same person.");
        }
        Optional<Ratings> existingRating = ratingRepository.findByCourierIdAndCustomerId(
                addRatingDto.getCourierId(), addRatingDto.getCustomerId());
        if (existingRating.isPresent()) {
            throw new IllegalArgumentException("Customer has already submitted feedback for this courier.");
        }
        Ratings rating = new Ratings();
        rating.setCourier(userRepository.findById(addRatingDto.getCourierId()).orElseThrow(() ->
                new EntityNotFoundException("Courier not found")));
        rating.setCustomer(userRepository.findById(addRatingDto.getCustomerId()).orElseThrow(() ->
                new EntityNotFoundException("Customer not found")));
        rating.setRating(addRatingDto.getRating());
        rating.setDescription(addRatingDto.getDescription());
        rating.setTimeCreation(LocalDateTime.now());

        ratingRepository.saveAndFlush(rating);
    }



    @Override
    public List<AllRatingsDto> allFeedbacks() {
        return ratingRepository.findAll().stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AllRatingsDto> getRatingsByCustomerId(UUID customerId) {
        return ratingRepository.findAllByCustomerId(customerId).stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AllRatingsDto> getRatingsByCourierId(UUID courierId) {
        return ratingRepository.findAllByCourierId(courierId).stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AllRatingsDto> getRating(UUID id) {
        return ratingRepository.findById(id).map(rating -> modelMapper.map(rating, AllRatingsDto.class));
    }
}
