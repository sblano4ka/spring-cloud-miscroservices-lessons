package com.svitlana.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Movie {

    private String movieId;
    private String name;
    private String description;
}
