package com.ulopez.moviestorage.services;

import com.ulopez.moviestorage.entity.Genre;
import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.repository.GenreRepository;
import com.ulopez.moviestorage.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository mr;

    @Autowired
    private GenreRepository gr;

    public List<Movie> getAll() {
        return this.mr.findAll();
    }

    @Transactional
    public Movie save(Movie m) {
        /*
           Checking if the movie genres exists.
           If not, create it.
         */
        /*
        m.getGenres().forEach( g -> {
            var entity = this.gr.getByName(g.getName());
            if(entity != null) {
                g.setId(entity.getId());
            } else {
                g.setId(this.gr.save(g).getId());
            }
        });*/
        m.setGenres(this.checkGenreExists(m.getGenres()));
        var newMovie = mr.save(m);
        return newMovie;
    }

    @Transactional
    public void delete(Long id) {
        var movie = this.mr.findById(id);
        if(movie.isPresent()) {
            this.mr.delete(movie.get());
        }
    }

    @Transactional
    public Movie update(Movie m, Long id) {
        var movie = this.mr.findById(id);
        if(movie.isPresent()) {
            var oldM = movie.get();
            oldM.setTitle(m.getTitle());
            oldM.setRating(m.getRating());
            oldM.setGenres(this.checkGenreExists(m.getGenres()));
            return this.mr.save(oldM);
        } else {
            m.setId(id);
            return this.mr.save(m);
        }
    }

    private List<Genre> checkGenreExists(List<Genre> l) {
        l.forEach( g -> {
            var entity = this.gr.getByName(g.getName());
            if(entity != null) {
                g.setId(entity.getId());
            } else {
                g.setId(this.gr.save(g).getId());
            }
        });

        return l;
    }

}
