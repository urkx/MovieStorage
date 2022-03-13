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
    public ResponseEntity delete(@RequestParam Long id) {
        this.ms.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateMovie")
    public ResponseEntity<Movie> update(@RequestBody Movie m, @RequestParam Long id) {
        return ResponseEntity.ok(this.ms.update(m, id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> findByYear(@RequestParam List<String> fields,
                                            @RequestParam(defaultValue = "") String title,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int size) {
        var movies = this.ms.findMoviesByYear(fields, title, page, size);
        if(movies.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }
}
