package com.example.bookmyshow.Controller;

import com.example.bookmyshow.Dtos.RequestDtos.AddTheaterDto;
import com.example.bookmyshow.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.example.bookmyshow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

  @Autowired
  private TheaterService theaterService;

  @PostMapping("/addTheater")
   public ResponseEntity addTheater(@RequestBody AddTheaterDto addTheaterDto)
   {
          return new ResponseEntity(theaterService.addTheater(addTheaterDto), HttpStatus.OK);
   }
   @PostMapping("/addTheaterSeats")
   public ResponseEntity addTheaterSeats(@RequestBody TheaterSeatEntryDto theaterSeatEntryDto)
   {
     return new ResponseEntity(theaterService.addTheaterSeats(theaterSeatEntryDto),HttpStatus.OK);
   }
}
