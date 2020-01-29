package com.svitlana.ratingdataservice.resources;

import com.svitlana.ratingdataservice.model.Rating;
import com.svitlana.ratingdataservice.model.UserRatings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @GetMapping(value = "/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping(value = "/users/{userId}")
    public UserRatings getRatings(@PathVariable("userId") String userId) {
        List<Rating> ratingList = Arrays.asList(
                new Rating("movieId1", 2),
                new Rating("movieId2", 5),
                new Rating("movieId3", 4)
        );
        UserRatings userRatings = new UserRatings();
        userRatings.setUserRating(ratingList);
        return userRatings;
    }
}
