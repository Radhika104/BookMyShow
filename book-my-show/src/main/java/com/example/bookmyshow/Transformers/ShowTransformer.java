package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDtos.AddShowDto;
import com.example.bookmyshow.Models.Show;

public class ShowTransformer {

  public static Show convertDtoToEntity(AddShowDto addShowDto)
  {
     Show show=Show.builder()
       .date(addShowDto.getDate())
       .time(addShowDto.getTime())
       .build();

     return show;
  }
}
