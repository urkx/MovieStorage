package com.ulopez.moviestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulopez.moviestorage.MoviestorageApplication;
import com.ulopez.moviestorage.entity.Genre;
import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.repository.GenreRepository;
import com.ulopez.moviestorage.repository.MovieRepository;
import com.ulopez.moviestorage.services.MovieService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(MoviestorageController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviestorageControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;
    @Autowired
    GenreRepository genreRepository;


    @Test
    public void getMovies_empty() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/moviestorage/getMovies")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void saveMovies_success() throws Exception {

        /*
            INPUT
         */
        var genre1 = new Genre("Comedy");
        var genre2 = new Genre("Drama");
        var listGenres = new ArrayList<Genre>();
        listGenres.add(genre1);
        listGenres.add(genre2);

        Movie movie = new Movie();
        movie.setGenres(listGenres);
        movie.setRating(8.76f);
        movie.setTitle("Movie 1");
        movie.setYearPremiered(1997);

        /*
            OUTPUT
         */
        var genre1Res = new Genre("Comedy");
        genre1Res.setId(1L);
        var genre2Res = new Genre("Drama");
        genre2Res.setId(2L);
        var listGenresRes = new ArrayList<Genre>();
        listGenresRes.add(genre1Res);
        listGenresRes.add(genre2Res);

        var movieRes = new Movie();
        movieRes.setId(3L);
        movieRes.setGenres(listGenresRes);
        movieRes.setRating(8.76f);
        movieRes.setTitle("Movie 1");
        movieRes.setYearPremiered(1997);


        /*
            REQUEST
         */
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/moviestorage/saveMovie")
                .contentType(MediaType.APPLICATION_JSON)
                //.accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movieRes)));
    }

}
