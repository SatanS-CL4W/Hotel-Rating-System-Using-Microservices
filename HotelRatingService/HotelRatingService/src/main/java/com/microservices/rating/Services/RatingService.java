package com.microservices.rating.Services;

import com.microservices.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating CreateRating(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //Get all by UserId
    List<Rating> getRatingByUserId(String UserId);

    //Get all by hotel
    List<Rating> getRatingByHotelId(String HotelId);
}
