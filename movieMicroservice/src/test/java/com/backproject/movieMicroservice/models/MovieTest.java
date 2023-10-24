package com.backproject.movieMicroservice.models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieTest {
    @Test
    void MovieCreatedSuccessfully()
    {
        Movie movie=new Movie();
        assertNotNull(movie);
    }



    @Test
    void MovieSettersAndGettersWorkSuccessfully()
    {
        Movie movie=new Movie();
        movie.setId(2);
        movie.setAdult(false);
        movie.setBackdrop_path("backpath");
        movie.setOverview("overview");
        movie.setPopularity(112.3);
        movie.setGenre_ids("[2,3]");
        movie.setTitle("Great Movie");
        movie.setOriginal_language("en");
        movie.setPoster_path("posterpath");
        Date date=Calendar.getInstance().getTime();
        movie.setRelease_date(date);
        movie.setVote_average(5.5);
        movie.setVote_count(200);
        movie.setVideo(false);
        assertAll(
                ()->assertEquals(2,movie.getId()),
                ()->assertFalse(movie.isAdult()),
                ()->assertEquals("backpath",movie.getBackdrop_path()),
                ()->assertEquals("overview",movie.getOverview()),
                ()->assertEquals(112.3,movie.getPopularity()),
                ()->assertEquals("[2,3]",movie.getGenre_ids()),
                ()->assertEquals("Great Movie",movie.getTitle()),
                ()->assertEquals("en",movie.getOriginal_language()),
                ()->assertEquals("posterpath",movie.getPoster_path()),
                ()->assertEquals(date,movie.getRelease_date()),
                ()->assertEquals(5.5,movie.getVote_average()),
                ()->assertEquals(200,movie.getVote_count()),
                ()->assertFalse(movie.isVideo())
        );
    }


    @Test
    void TwoMoviesAreEqual()
    {
        Movie movie1=new Movie();
        Movie movie2=new Movie();
        movie1.setTitle("Movie");
        movie1.setId(5);
        movie2.setTitle("Movie");
        movie2.setId(5);
        assertTrue(movie1.equals(movie2));
    }

    @Test
    void TwoMoviesAsStringsAreEqual()
    {
        Movie movie1=new Movie();
        Movie movie2=new Movie();
        movie1.setTitle("Movie");
        movie1.setId(5);
        movie2.setTitle("Movie");
        movie2.setId(5);
        String movie1AsString=movie1.toString();
        String movie2AsString=movie2.toString();
        assertTrue(movie1.equals(movie2));
    }

    @Test
    void TwoMoviesAsHashCodesAreEqual()
    {
        Movie movie1=new Movie();
        Movie movie2=new Movie();
        movie1.setTitle("Movie");
        movie1.setId(5);
        movie2.setTitle("Movie");
        movie2.setId(5);
        int hash1=movie1.hashCode();
        int hash2=movie2.hashCode();
        assertTrue(hash1==hash2);
    }


}
