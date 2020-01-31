package com.svitlana.movieinfoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieSummary {

    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
}
