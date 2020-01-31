package com.svitlana.movieinfoservice.resources;

import com.svitlana.movieinfoservice.model.Movie;
import com.svitlana.movieinfoservice.model.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary forObject = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieId, forObject.getOriginalTitle(), forObject.getOverview());
    }
}
