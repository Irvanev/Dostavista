package com.example.dostavista.contollers;

import com.example.dostavista.dtos.ratingsDtos.AddRatingDto;
import com.example.dostavista.dtos.ratingsDtos.AllRatingsDto;
import com.example.dostavista.services.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Отзывы", description = "Взаимодействие с отзывами")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @Operation(summary = "Добавление отзыва", description = "Позволяет добавть отзыв")
    @PostMapping
    public void addFeedback(@RequestBody @Valid AddRatingDto addRatingDto) {
        ratingService.addFeedback(addRatingDto);

    }

    @Operation(summary = "Поиск отзывов пользвателя", description = "Позволяет посмотреть все написанные отзывы пользвователем")
    @GetMapping("findByCustomerId/{customerId}")
    public List<AllRatingsDto> getAllRatingsByCustomerId(@PathVariable UUID customerId) {
        return ratingService.getRatingsByCustomerId(customerId);
    }

    @Operation(summary = "Поиск отзывов пользвателя", description = "Позволяет посмотреть все написанные отзывы пользвователем")
    @GetMapping("/{id}")
    public AllRatingsDto getRating(@PathVariable UUID id) {
        return ratingService.getRating(id)
                .map(rating -> {
                    rating.add(linkTo(methodOn(RatingController.class).getRating(id))
                            .withSelfRel());
                    return rating;
                })
                .orElse(null);
    }

    @Operation(summary = "Поиск отзывов курьера", description = "Позволяет посмотреть все написанные отзывы пользовтеля курьру")
    @GetMapping("findByCourierId/{courierId}")
    public List<AllRatingsDto> getAllRatingsByCourierId(@PathVariable UUID courierId) {
        return ratingService.getRatingsByCourierId(courierId);
    }

    @Operation(summary = "Просмотр всех отзывов", description = "Позволяет посмотреть все написанные отзывы пользователей")
    @GetMapping
    public ResponseEntity<CollectionModel<AllRatingsDto>> allRatings() {
        List<AllRatingsDto> ratings = ratingService.allFeedbacks();
        ratings.forEach(rating -> {
            rating.add(linkTo(methodOn(RatingController.class).getRating(rating.getId())).withSelfRel());
        });
        Link allRatingsLink = linkTo(methodOn(RatingController.class).allRatings())
                .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(ratings, allRatingsLink));
    }
}
