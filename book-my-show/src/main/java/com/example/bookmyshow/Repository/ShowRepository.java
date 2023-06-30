package com.example.bookmyshow.Repository;

import com.example.bookmyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Integer> {

  @Query(value = "select * from shows where movie_id =:movieId and theater_id =:theaterId and date =:showDate",nativeQuery = true)
  public List<Show> getAllShows(int movieId, int theaterId, String showDate);
}
