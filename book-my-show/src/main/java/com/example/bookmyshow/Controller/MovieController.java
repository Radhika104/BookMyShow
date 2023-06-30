package com.example.bookmyshow.Controller;


import com.example.bookmyshow.Dtos.RequestDtos.AddMovieDto;
import com.example.bookmyshow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @PostMapping("/addMovie")
  public ResponseEntity addMovie(@RequestBody AddMovieDto addMovieDto)
  {
    try {
      return new ResponseEntity(movieService.addMovie(addMovieDto), HttpStatus.OK);
    }
    catch(Exception e)
    {
      return new ResponseEntity<>("FAILURE : "+e.getMessage(),HttpStatus.BAD_REQUEST);
    }
  }
}
