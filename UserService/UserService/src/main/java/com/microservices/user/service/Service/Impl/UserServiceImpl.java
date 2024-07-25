package com.microservices.user.service.Service.Impl;

import com.microservices.user.service.Service.UserService;
import com.microservices.user.service.entities.Hotel;
import com.microservices.user.service.entities.Rating;
import com.microservices.user.service.entities.User;
import com.microservices.user.service.exceptions.ResourceNotFoundException;
import com.microservices.user.service.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String userid) {

        User user = userRepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User with this id not found on the server!!! : " + userid));
        Rating[] getUserRatings = restTemplate.getForObject("http://HOTELRATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);

        logger.info("{}",getUserRatings);
        List<Rating> ratings = Arrays.stream(getUserRatings).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUserById(String id) {
        userRepo.deleteById(id);

    }

    @Override
    public void updateUser(String id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with this id not found on the server!!! : " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());

        userRepo.save(existingUser);
    }
}
