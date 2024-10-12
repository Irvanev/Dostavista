package com.example.dostavista.repositories;

import com.example.dostavista.models.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Ratings, UUID> {
    Optional<Ratings> findByCourierIdAndCustomerId(UUID courierId, UUID customerId);
    List<Ratings> findAllByCustomerId(UUID customerId);
    List<Ratings> findAllByCourierId(UUID courierId);
}
