package com.example.bookmyshow.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

  private String name;
  private String emailId;
  private int age;
  private String mobNo;

  private String statusCode;
  private String statusMessage;
}
