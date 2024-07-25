package com.microservices.rating.controller;

import com.microservices.rating.Services.RatingService;
import com.microservices.rating.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {

        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.CreateRating(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {

        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {

        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {

        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
