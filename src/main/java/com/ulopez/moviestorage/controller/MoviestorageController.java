package com.ulopez.moviestorage.controller;

import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviestorage")
public class MoviestorageController {

    @Autowired
    private MovieService ms;

    /**
     *  Returns all movies stored.
     * @return The list of movies stored (200 OK).
     * If the list is empty, returns no content response.
     */
    @GetMapping("/getMovies")
    public ResponseEntity<List<Movie>> getAll() {
        var movies = this.ms.getAll();
        if(movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }

    /**
     * Saves the movie defined as JSON in the request body.
     * @param m The movie to save
     * @return OK response with the new movie stored.
     */
    @PostMapping("/saveMovie")
    public ResponseEntity<Movie> save(@RequestBody Movie m) {
        var newMovie = this.ms.save(m);
        return ResponseEntity.ok(newMovie);
    }

    /**
     * Deletes the movie which ID is specified as parameter.
     * @param id ID of the movie to delete
     * @return No content response
     */
    @DeleteMapping("/deleteMovie")
    public ResponseEntity delete(@RequestParam Long id) {
        this.ms.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the movie which ID is specified as parameter.
     * The new content of the movie is defined as JSON in the request body.
     * The new
     * @param m The new movie
     * @param id ID of the movie to update
     * @return OK response. The new movie is in the response body.
     */
    @PutMapping("/updateMovie")
    public ResponseEntity<Movie> update(@RequestBody Movie m, @RequestParam Long id) {
        return ResponseEntity.ok(this.ms.update(m, id));
    }

    /**
     * Returns a filtered, sorted and paginated list of movies.
     * @param fields List of fields to order by.
     * @param title Title filter.
     * @param page Number of the page to return.
     * @param size Size of the page to return.
     * @return The list of filtered movies stored (200 OK).
     * If the list is empty, returns no content response.
     */
    @GetMapping("/list")
    public ResponseEntity<List<Movie>> findByYear(@RequestParam List<String> fields,
                                            @RequestParam(defaultValue = "") String title,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int size) {
        var movies = this.ms.findMoviesFiltered(fields, title, page, size);
        if(movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }
}
