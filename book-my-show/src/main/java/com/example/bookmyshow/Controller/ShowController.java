package com.example.bookmyshow.Controller;


import com.example.bookmyshow.Dtos.RequestDtos.AddShowDto;
import com.example.bookmyshow.Dtos.RequestDtos.GetAllShowsForMovieRequestDto;
import com.example.bookmyshow.Dtos.RequestDtos.ShowSeatsDto;
import com.example.bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

  @Autowired
  private ShowService showService;

  @PostMapping("/addShow")
   public ResponseEntity addShow(@RequestBody AddShowDto addShowDto)
   {
     try {
        return new ResponseEntity<String>(showService.addShow(addShowDto), HttpStatus.OK);
     }
     catch(Exception e)
     {
       return new ResponseEntity<String>("FAILURE, having issue : "+e.getMessage(),HttpStatus.NOT_FOUND);
     }
   }

   @PostMapping("/associateShoeSeats")
   public ResponseEntity associateShowSeats(@RequestBody ShowSeatsDto showSeatsDto) {
     try {
       return new ResponseEntity<String>(showService.associateShowSeat(showSeatsDto), HttpStatus.OK);
     } catch (Exception e) {
       return new ResponseEntity<String>("FAILURE, having issue : " + e.getMessage(), HttpStatus.NOT_FOUND);
     }
   }

   @GetMapping("/getAllShowsForMovieInTheater")
   public ResponseEntity getAllShowsForMovieInTheater(@RequestBody GetAllShowsForMovieRequestDto getAllShowsForMovieRequestDto)
   {
     return new ResponseEntity(showService.getAllShowsForMovieInTheater(getAllShowsForMovieRequestDto),HttpStatus.OK);
   }
}
