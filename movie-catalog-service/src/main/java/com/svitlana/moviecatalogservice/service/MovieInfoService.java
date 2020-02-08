package com.svitlana.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.svitlana.moviecatalogservice.model.CatalogItem;
import com.svitlana.moviecatalogservice.model.Movie;
import com.svitlana.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"),
                @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public CatalogItem getCatalogItem(final Rating rating) {
        String movieInfoUrl = "http://movie-info-service:8083/movies/";
        Movie movie = restTemplate.getForObject(movieInfoUrl + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getMovieId(), movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(final Rating rating) {
        return new CatalogItem("No movie", "", "", rating.getRating());
    }

}
