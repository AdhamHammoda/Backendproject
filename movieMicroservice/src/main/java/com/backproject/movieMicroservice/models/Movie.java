package com.backproject.movieMicroservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

import java.util.Date;
@Data
@Table(name="movie")
@Entity
public class Movie {
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "adult")
    boolean adult;
    @Column(name = "backdrop_path")
    String backdrop_path;
    @Column(name = "genre_ids")
    String genre_ids;
    @Column(name = "original_language")
    String original_language;
    @Column(name = "original_title")
    String original_title;
    @Column(name = "overview")
    String overview;
    @Column(name = "popularity")
    double popularity;
    @Column(name = "poster_path")
    String poster_path;
    @Column(name = "release_date")
    Date release_date;
    @Column(name = "title")
    String title;
    @Column(name = "video")
    boolean video;
    @Column(name = "vote_average")
    double vote_average;
    @Column(name = "vote_count")
    int vote_count;

}
