package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDtos.AddMovieDto;
import com.example.bookmyshow.Models.Movie;

public class MovieTransformer {
  public static Movie convertDtoToEntity(AddMovieDto addMovieDto)
  {
    Movie movie=Movie.builder()
      .name(addMovieDto.getName())
      .genre(addMovieDto.getGenre())
      .duration(addMovieDto.getDuration())
      .rating(addMovieDto.getRating())
      .releaseDate(addMovieDto.getReleaseDate())
      .language(addMovieDto.getLanguage())
      .build();

    return movie;
  }
}
