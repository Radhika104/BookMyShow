package com.example.bookmyshow.Dtos.RequestDtos;

import lombok.Data;

@Data
public class TheaterSeatEntryDto {

   private int noOfSeatAtOneRow;
   private int noOfClassicSeats;
   private int noOfPremiumSeats;
   private String location;
}
