package com.example.bookmyshow.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto {

  private LocalTime time;
  private Date date;
  private int movieId;
  private int theaterId;
}
