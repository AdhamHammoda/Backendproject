package com.backproject.movieMicroservice.dao;

import com.backproject.movieMicroservice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Movie,Integer> {
}
