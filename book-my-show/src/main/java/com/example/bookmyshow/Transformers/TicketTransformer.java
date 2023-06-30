package com.example.bookmyshow.Transformers;


import com.example.bookmyshow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.bookmyshow.Models.Ticket;

import java.util.List;

public class TicketTransformer {

  public static String convertListToString(List<?> list)
  {
    String result="";
     for(int i=0;i<list.size();i++)
     {
       if(i!=list.size()-1) result+=list.get(i)+",";
       else result+=list.get(i);
     }
     return result;
  }
  public static TicketResponseDto convertEntityToDto(Ticket ticket)
  {
    TicketResponseDto ticketResponseDto=TicketResponseDto.builder()
      .theaterName(ticket.getShow().getTheater().getName())
      .location(ticket.getShow().getTheater().getLocation())
      .movieName(ticket.getShow().getMovie().getName())
      .bookedSeatsNo(ticket.getBookedSeats())
      .seatsHavingRefreshments(ticket.getSeatsHavingRefreshments())
      .totalPrice(ticket.getTotalTicketPrice())
      .showDate(ticket.getShow().getDate())
      .localTime(ticket.getShow().getTime())
      .build();

    return ticketResponseDto;
  }
}
