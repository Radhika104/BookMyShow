package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

  public Optional<Movie> findByName(String name);
}
