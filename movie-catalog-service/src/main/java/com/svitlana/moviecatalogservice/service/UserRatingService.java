package com.svitlana.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.svitlana.moviecatalogservice.model.Rating;
import com.svitlana.moviecatalogservice.model.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            })
    public UserRatings getUserRatings(@PathVariable("userId") final String userId) {
        String userRatingsUrl = "http://ratings-data-service/ratingsdata/users/";
        return restTemplate.getForObject(userRatingsUrl + userId, UserRatings.class);
    }

    private UserRatings getFallbackUserRating(final String userId) {
        UserRatings userRatings = new UserRatings();
        userRatings.setUserId(userId);
        userRatings.setUserRating(Arrays.asList(new Rating("No movie", 0)));
        return userRatings;
    }
}
