package com.ulopez.moviestorage.repository;

import com.ulopez.moviestorage.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Genre getByName(String name);
}
