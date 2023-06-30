package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.RequestDtos.AddMovieDto;
import com.example.bookmyshow.Exceptions.MovieAlreadyExistsException;
import com.example.bookmyshow.Models.Movie;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  public String addMovie(AddMovieDto addMovieDto) throws MovieAlreadyExistsException
  {
    Optional<Movie> movieOptional=movieRepository.findByName(addMovieDto.getName());
    if(movieOptional.isPresent()) throw new MovieAlreadyExistsException("Movie Already Exists");
    Movie movie= MovieTransformer.convertDtoToEntity(addMovieDto);
    movieRepository.save(movie);
    return "Movie added Successfully";
  }
}
