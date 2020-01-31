package com.svitlana.ratingdataservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class UserRatings {
    private List<Rating> userRating;

    public UserRatings() {
        this.userRating = Arrays.asList(
                new Rating("38700-bad-boys-3", 2),
                new Rating("419704-ad-astra", 5),
                new Rating("359724-ford-v-ferrari", 4)
        );
    }
}
