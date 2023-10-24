package com.backproject.movieMicroservice.services;


import com.backproject.movieMicroservice.dao.MovieDAO;
import com.backproject.movieMicroservice.models.Movie;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class MovieServiceTest {

    @Mock
    private MovieDAO movieDAO;

    @InjectMocks
    private MovieService movieService;

    @Test
    void getAllMoviesSuccessfully()
    {
        List<Movie> list = List.of(new Movie());
        Mockito.when(movieDAO.findAll()).thenReturn(list);
        assertEquals(list,movieService.getMoviesData());
    }

    @Test
    void getMovieSuccessfully()
    {
        Movie movie=new Movie();
        movie.setId(2);
        Mockito.when(movieDAO.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(movie));
        Movie gotMovie=movieService.getMovie(2);
        assertEquals(gotMovie,movie);
    }

    @Test
    void getMoviesPaginated()
    {
        List<Movie> movies=List.of(new Movie());
        Page page=Mockito.mock(Page.class);
        Pageable paging = PageRequest.of(1,5, Sort.by("id").ascending());
        Mockito.when(movieDAO.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        Mockito.when(page.getContent()).thenReturn(movies);
        List<Movie> list=movieService.getMoviesDataByPage(1);
        assertEquals(movies,list);
    }

    @Test
    void addMovieSuccessfully()
    {
        Movie movie=new Movie();
        movie.setId(2);
        Mockito.when(movieDAO.save(Mockito.any(Movie.class))).thenReturn(movie);
        assertEquals(movie,movieService.addMovie(movie));
    }


}
