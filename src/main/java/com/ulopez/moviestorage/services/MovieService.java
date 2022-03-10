package com.ulopez.moviestorage.services;

import com.ulopez.moviestorage.entity.Genre;
import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.repository.GenreRepository;
import com.ulopez.moviestorage.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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
        m.setGenres(this.checkGenreExists(m.getGenres()));
        return mr.save(m);
    }

    @Transactional
    public void delete(Long id) {
        var movie = this.mr.findById(id);
        movie.ifPresent(value -> this.mr.delete(value));
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
            g.setId(Objects.requireNonNullElseGet(entity, () -> this.gr.save(g)).getId());
        });

        return l;
    }

    public List<Movie> findMoviesByYear(String field, int page, int size) {
        return this.mr.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field))).getContent();
    }
}
