package com.svitlana.ratingdataservice.resources;

import com.svitlana.ratingdataservice.model.Rating;
import com.svitlana.ratingdataservice.model.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @GetMapping(value = "/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping(value = "/users/{userId}")
    public UserRatings getRatings(@PathVariable("userId") String userId) {
        return new UserRatings();
    }
}
