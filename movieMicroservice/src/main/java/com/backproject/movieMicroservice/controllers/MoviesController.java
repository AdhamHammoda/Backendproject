package com.backproject.movieMicroservice.controllers;


import com.backproject.movieMicroservice.models.Movie;
import com.backproject.movieMicroservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    MovieService movieService;
    @GetMapping(value = "/get-movie")
    public List<Movie> getAllMovies()  {
        return movieService.getMoviesData();
    }
    @GetMapping(value = "/get-movie/page-{pageNumber}")
    public List<Movie> getMovies(@PathVariable("pageNumber") int pageNumber) {
        return movieService.getMoviesDataByPage(pageNumber);
    }

    @GetMapping(value = "/get-movie/{id}")
    public Movie getMoviebyId(@PathVariable("id") int id)  {
        return movieService.getMovie(id);
    }

    @PostMapping(value = "/add-movie")
    public void addMovie(@RequestBody Movie movie)
    {
        movieService.addMovie(movie);
    }
}
