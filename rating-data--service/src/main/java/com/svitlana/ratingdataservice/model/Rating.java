package com.svitlana.ratingdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Rating {

    private String movieId;
    private int rating;
}
