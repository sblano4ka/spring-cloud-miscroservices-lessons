package com.svitlana.moviecatalogservice.resources;

import com.svitlana.moviecatalogservice.model.CatalogItem;
import com.svitlana.moviecatalogservice.model.UserRatings;
import com.svitlana.moviecatalogservice.service.MovieInfoService;
import com.svitlana.moviecatalogservice.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private UserRatingService userratingService;

// Can be used to advanced load balancing and getting more precise info from eureka
//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRatings usersRatings = userratingService.getUserRatings(userId);
        return usersRatings.getUserRating()
                .stream()
                .map(userRating -> movieInfoService.getCatalogItem(userRating))
                .collect(Collectors.toList());
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
