package com.example.bookmyshow.Transformers;


import com.example.bookmyshow.Dtos.RequestDtos.AddTheaterDto;
import com.example.bookmyshow.Models.Theater;

public class TheaterTransformer {

  public static Theater convertDtoToEntity(AddTheaterDto addTheaterDto)
  {
       Theater theater=Theater.builder()
         .name(addTheaterDto.getName())
         .location(addTheaterDto.getLocation())
         .build();

       return theater;
  }
}
