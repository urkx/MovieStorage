package com.ulopez.moviestorage.services;

import com.ulopez.moviestorage.entity.Genre;
import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.repository.GenreRepository;
import com.ulopez.moviestorage.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository mr;

    @Autowired
    private GenreRepository gr;

    /**
     *
     * @return All movies stored.
     */
    public List<Movie> getAll() {
        return this.mr.findAll();
    }

    /**
     * Saves a new movie.
     * The list of genres will be saved if not.
     * @param m The new movie (without ID).
     * @return The new movie (with ID).
     */
    @Transactional
    public Movie save(Movie m) {
        m.setGenres(this.checkGenreExists(m.getGenres()));
        return this.mr.save(m);
    }

    /**
     * Deletes a movie.
     * @param id ID of the movie to delete.
     */
    @Transactional
    public void delete(Long id) {
        var movie = this.mr.findById(id);
        movie.ifPresent(value -> this.mr.delete(value));
    }

    /**
     * Updates a movie.
     * @param m New movie values.
     * @param id ID of the movie to update.
     * @return New movie values.
     */
    @Transactional
    public Movie update(Movie m, Long id) {
        var movie = this.mr.findById(id);
        if(movie.isPresent()) {
            var oldM = movie.get();
            oldM.setTitle(m.getTitle());
            oldM.setRating(m.getRating());
            oldM.setYearPremiered(m.getYearPremiered());
            oldM.setGenres(this.checkGenreExists(m.getGenres()));
            return this.mr.save(oldM);
        } else {
            m.setId(id);
            return save(m);
        }
    }

    /**
     * Returns a filtered, sorted and paginated list of movies.
     * @param fields List of fields to order by.
     * @param title Title filter.
     * @param page Number of the page to return.
     * @param size Size of the page to return.
     * @return The list of filtered movies stored.
     */
    public List<Movie> findMoviesFiltered(List<String> fields, String title,  int page, int size) {
        if("".equals(title)) {
            return this.mr.findAll(PageRequest.of(page, size, Sort.by(generateOrders(fields)))).getContent();
        } else {
            return this.mr.findByTitleContaining(title,
                    PageRequest.of(page, size, Sort.by(generateOrders(fields)))
                    ).getContent();
        }
    }

    /**
     * Generates a list of Order objects.
     * @param fields List of fields.
     * @return A list of Order objects.
     */
    private List<Order> generateOrders(List<String> fields) {
        return fields.stream().map(Order::desc).collect(Collectors.toList());
    }

    /**
     * Checks if the genres exist.
     * If it is, the entity is returned from DB.
     * If not, it will be saved.
     * @param l List of genres
     * @return List of genres
     */
    private List<Genre> checkGenreExists(List<Genre> l) {
        l.forEach( g -> {
            var entity = this.gr.getByName(g.getName());
            g.setId(Objects.requireNonNullElseGet(entity, () -> this.gr.save(g)).getId());
        });
        return l;
    }
}
