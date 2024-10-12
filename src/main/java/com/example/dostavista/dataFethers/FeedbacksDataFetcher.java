package com.example.dostavista.dataFethers;

import com.example.dostavista.dtos.ratingsDtos.AddRatingDto;
import com.example.dostavista.dtos.ratingsDtos.AllRatingsDto;
import com.example.dostavista.models.entities.Ratings;
import com.example.dostavista.repositories.RatingRepository;
import com.example.dostavista.repositories.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@DgsComponent
public class FeedbacksDataFetcher {
    private RatingRepository ratingRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public FeedbacksDataFetcher(RatingRepository ratingRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @DgsQuery
    public List<AllRatingsDto> getFeedbacks() {
        return ratingRepository.findAll().stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @DgsQuery
    public List<AllRatingsDto> findFeedbackByCustomerId(@InputArgument UUID customerId) {
        return ratingRepository.findAllByCustomerId(customerId).stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @DgsQuery
    public List<AllRatingsDto> findFeedbackByCourierId(@InputArgument UUID courierId) {
        return ratingRepository.findAllByCourierId(courierId).stream().map(order -> modelMapper.map(order, AllRatingsDto.class))
                .collect(Collectors.toList());
    }

    @DgsMutation
    public void addFeedback(@InputArgument(name = "feedback") AddRatingDto addRatingDto) {
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
}
