package com.backproject.movieMicroservice.services;

import com.backproject.movieMicroservice.dao.MovieDAO;
import com.backproject.movieMicroservice.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieDAO movieDAO;
    public List<Movie> getMoviesData()
    {
        System.out.println("hhhh");
        return movieDAO.findAll();
    }
    public void addMovie(Movie movie)
    {
        movieDAO.save(movie);
    }
    public Movie getMovie(int id)
    {
        Movie movie=movieDAO.findById(id).get();
        return movie;
    }
    public List<Movie> getMoviesDataByPage(int pageNumber)
    {
        Pageable paging = PageRequest.of(pageNumber-1,5, Sort.by("id").ascending());
        Page<Movie> page=movieDAO.findAll(paging);
        return page.getContent();
    }
}
