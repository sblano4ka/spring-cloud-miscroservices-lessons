package com.svitlana.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CatalogItem {
    private String movieId;
    private String name;
    private String desc;
    private int rating;
}
