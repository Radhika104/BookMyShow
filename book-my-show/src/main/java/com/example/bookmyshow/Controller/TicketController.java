package com.example.bookmyshow.Controller;

import com.example.bookmyshow.Dtos.RequestDtos.TicketRequestDto;
import com.example.bookmyshow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Service.TicketService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @PostMapping("/bookTicket")
  public ResponseEntity bookTicket(@RequestBody TicketRequestDto ticketRequestDto)
  {
    try
    {
      TicketResponseDto ticketResponseDto=ticketService.bookTicket(ticketRequestDto);
      return new ResponseEntity<TicketResponseDto>(ticketResponseDto,HttpStatus.OK);
    }catch (Exception e)
    {
      return new ResponseEntity("FAILURE : "+e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}

