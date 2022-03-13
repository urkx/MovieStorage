package com.ulopez.moviestorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ulopez.moviestorage.entity.Genre;
import com.ulopez.moviestorage.entity.Movie;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MoviestorageControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @Test
    public void test1SaveMovies() throws Exception {

        /*
            INPUT
         */
        var genre1 = new Genre("Comedy");
        var genre2 = new Genre("Drama");
        var listGenres1 = new ArrayList<Genre>();
        listGenres1.add(genre1);
        listGenres1.add(genre2);

        var movie1 = new Movie();
        movie1.setGenres(listGenres1);
        movie1.setRating(8.76f);
        movie1.setTitle("Movie 1");
        movie1.setYearPremiered(1997);

        var genre3 = new Genre("Thriller");
        var listGenres2 = new ArrayList<Genre>();
        listGenres2.add(genre3);

        var movie2 = new Movie();
        movie2.setGenres(listGenres2);
        movie2.setRating(7.3f);
        movie2.setTitle("Java8");
        movie2.setYearPremiered(2007);

        var listGenres3 = new ArrayList<Genre>();
        listGenres3.add(genre1);
        listGenres3.add(genre3);

        var movie3 = new Movie();
        movie3.setGenres(listGenres3);
        movie3.setRating(5.2f);
        movie3.setTitle("Java11");
        movie3.setYearPremiered(2011);

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

        var genre3Res = new Genre("Thriller");
        genre3Res.setId(4L);
        var listGenres2Res = new ArrayList<Genre>();
        listGenres2Res.add(genre3Res);

        var movie2Res = new Movie();
        movie2Res.setId(5L);
        movie2Res.setGenres(listGenres2Res);
        movie2Res.setRating(7.3f);
        movie2Res.setTitle("Java8");
        movie2Res.setYearPremiered(2007);

        var listGenres3Res = new ArrayList<Genre>();
        listGenres3Res.add(genre1Res);
        listGenres3Res.add(genre3Res);

        var movie3Res = new Movie();
        movie3Res.setId(6L);
        movie3Res.setGenres(listGenres3Res);
        movie3Res.setRating(5.2f);
        movie3Res.setTitle("Java11");
        movie3Res.setYearPremiered(2011);

        /*
            REQUESTS
         */
        MockHttpServletRequestBuilder mockRequest1 = MockMvcRequestBuilders.post("/moviestorage/saveMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie1));

        mockMvc.perform(mockRequest1)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movieRes)));

        MockHttpServletRequestBuilder mockRequest2 = MockMvcRequestBuilders.post("/moviestorage/saveMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie2));

        mockMvc.perform(mockRequest2)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movie2Res)));

        MockHttpServletRequestBuilder mockRequest3 = MockMvcRequestBuilders.post("/moviestorage/saveMovie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie3));

        mockMvc.perform(mockRequest3)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movie3Res)));
    }

    @Test
    public void test2GetMovies() throws Exception {
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

        var genre3Res = new Genre("Thriller");
        genre3Res.setId(4L);
        var listGenres2Res = new ArrayList<Genre>();
        listGenres2Res.add(genre3Res);

        var movie2Res = new Movie();
        movie2Res.setId(5L);
        movie2Res.setGenres(listGenres2Res);
        movie2Res.setRating(7.3f);
        movie2Res.setTitle("Java8");
        movie2Res.setYearPremiered(2007);

        var listGenres3Res = new ArrayList<Genre>();
        listGenres3Res.add(genre1Res);
        listGenres3Res.add(genre3Res);

        var movie3Res = new Movie();
        movie3Res.setId(6L);
        movie3Res.setGenres(listGenres3Res);
        movie3Res.setRating(5.2f);
        movie3Res.setTitle("Java11");
        movie3Res.setYearPremiered(2011);

        var movies = new ArrayList<Movie>();
        movies.add(movieRes);
        movies.add(movie2Res);
        movies.add(movie3Res);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/moviestorage/getMovies")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movies)));
    }

    @Test
    public void test3UpdateMovie() throws Exception {
        /*
            INPUT
         */
        var id = 3L;

        var genre1 = new Genre("Fantasy");
        var listGenres = new ArrayList<Genre>();
        listGenres.add(genre1);

        var movie = new Movie();
        movie.setGenres(listGenres);
        movie.setRating(5.68f);
        movie.setTitle("Movie 1 Modified");
        movie.setYearPremiered(2004);
        movie.setId(id);

        /*
            OUTPUT
         */
        var genre1Res = new Genre("Fantasy");
        genre1Res.setId(7L);
        var listGenresRes = new ArrayList<Genre>();
        listGenresRes.add(genre1Res);

        var movieRes = new Movie();
        movieRes.setGenres(listGenresRes);
        movieRes.setRating(5.68f);
        movieRes.setTitle("Movie 1 Modified");
        movieRes.setYearPremiered(2004);
        movieRes.setId(id);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/moviestorage/updateMovie?id=" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(movie));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movieRes)));
    }

    @Test
    public void test4DeleteMovie() throws Exception {
        var id = 3L;

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/moviestorage/deleteMovie?id=" + id);

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void test5List() throws Exception {
        var genre1Res = new Genre("Comedy");
        genre1Res.setId(1L);
        var genre3Res = new Genre("Thriller");
        genre3Res.setId(4L);
        var listGenres2Res = new ArrayList<Genre>();
        listGenres2Res.add(genre3Res);

        var movie2Res = new Movie();
        movie2Res.setId(5L);
        movie2Res.setGenres(listGenres2Res);
        movie2Res.setRating(7.3f);
        movie2Res.setTitle("Java8");
        movie2Res.setYearPremiered(2007);

        var listGenres3Res = new ArrayList<Genre>();
        listGenres3Res.add(genre1Res);
        listGenres3Res.add(genre3Res);

        var movie3Res = new Movie();
        movie3Res.setId(6L);
        movie3Res.setGenres(listGenres3Res);
        movie3Res.setRating(5.2f);
        movie3Res.setTitle("Java11");
        movie3Res.setYearPremiered(2011);

        var movies = new ArrayList<Movie>();
        movies.add(movie3Res);
        movies.add(movie2Res);

        MockHttpServletRequestBuilder mockRequest1 = MockMvcRequestBuilders
                .get("/moviestorage/list?fields=rating,yearPremiered&size=3&page=0&title=Ja");

        mockMvc.perform(mockRequest1)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(movies)));

        MockHttpServletRequestBuilder mockRequest2 = MockMvcRequestBuilders
                .get("/moviestorage/list?fields=rating,yearPremiered&size=3&page=1&title=Ja");

        mockMvc.perform(mockRequest2)
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
