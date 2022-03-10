package com.ulopez.moviestorage.controller;

import com.ulopez.moviestorage.entity.Movie;
import com.ulopez.moviestorage.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviestorage")
public class MoviestorageController {

    @Autowired
    private MovieService ms;

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping("/getMovies")
    public ResponseEntity<List<Movie>> getAll() {
        var movies = this.ms.getAll();
        if(movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/saveMovie")
    public ResponseEntity<Movie> save(@RequestBody Movie m) {
        var newMovie = this.ms.save(m);
        return ResponseEntity.ok(newMovie);
    }

    @DeleteMapping("/deleteMovie")
    public void delete(@RequestParam Long id) {
        this.ms.delete(id);
    }

    @PutMapping("/updateMovie")
    public Movie update(@RequestBody Movie m, @RequestParam Long id) {
        return this.ms.update(m, id);
    }

    @GetMapping("/findByYear")
    public ResponseEntity<List<Movie>> findByYear(@RequestParam int year,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int size) {
        var movies = this.ms.findMoviesByYear(year, page, size);
        return ResponseEntity.ok(movies);
    }
}
