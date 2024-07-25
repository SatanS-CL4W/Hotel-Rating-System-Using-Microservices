package com.microservices.rating.Services.impl;

import com.microservices.rating.Services.RatingService;
import com.microservices.rating.entities.Rating;
import com.microservices.rating.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating CreateRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String UserId) {
        return ratingRepository.findByUserId(UserId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String HotelId) {
        return ratingRepository.findByHotelId(HotelId);
    }
}
