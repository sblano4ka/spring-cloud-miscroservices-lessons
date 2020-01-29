package com.svitlana.moviecatalogservice.resources;

import com.svitlana.moviecatalogservice.model.CatalogItem;
import com.svitlana.moviecatalogservice.model.Movie;
import com.svitlana.moviecatalogservice.model.Rating;
import com.svitlana.moviecatalogservice.model.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    // Can be used to advanced load balancing and getting more precise info from eureka
    @Autowired
    private DiscoveryClient discoveryClient;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        // get user rated movies
        String userRatingsUrl = "http://ratings-data-service/ratingsdata/users/";
        UserRatings usersRatings = restTemplate.getForObject(userRatingsUrl + userId, UserRatings.class);

        // get movie info
        return usersRatings.getUserRating()
                .stream()
                .map(rating -> getCatalogItem(restTemplate, rating))
                .collect(Collectors.toList());
    }

    private CatalogItem getCatalogItem(final RestTemplate restTemplate, final Rating rating) {
        // get movie details
        String movieInfoUrl = "http://movie-info-service:8083/movies/";
        Movie movie = restTemplate.getForObject(movieInfoUrl + rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getMovieId(), movie.getName(), movie.getDescription(), rating.getRating());
    }

//    private void webFlux() {
//            Movie movie = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8083/movies/" + "rating.getMovieId()")
//                .retrieve()
//                .bodyToMono(Movie.class)
//                .block();
//    }
}
