package com.example.bookmyshow.Dtos.ResponseDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class GetAllShowsForMovieResponseDto {

  private int showId;
  private LocalTime showTime;
  private Date date;
}
