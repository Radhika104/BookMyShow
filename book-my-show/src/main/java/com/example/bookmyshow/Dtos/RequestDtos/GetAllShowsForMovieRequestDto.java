package com.example.bookmyshow.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class GetAllShowsForMovieRequestDto {

  private int movieId;
  private int theaterId;
  private String date;
}
