package com.example.bookmyshow.Dtos.RequestDtos;

import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class AddMovieDto {

  private String name;
  private double duration;
  private Date releaseDate;
  private double rating;
  private Genre genre;
  private Language language;
}
