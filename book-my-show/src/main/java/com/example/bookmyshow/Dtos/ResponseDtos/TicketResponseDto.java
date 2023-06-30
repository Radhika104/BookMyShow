package com.example.bookmyshow.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {

  private String responseMessage;
  private LocalTime localTime;
  private Date showDate;
  private String movieName;
  private String theaterName;
  private String location;
  private String bookedSeatsNo;
  private String seatsHavingRefreshments;
  private int totalPrice; //include ticketprice + refreshments if added
}
