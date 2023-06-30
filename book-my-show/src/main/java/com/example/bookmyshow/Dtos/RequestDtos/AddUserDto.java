package com.example.bookmyshow.Dtos.RequestDtos;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class AddUserDto {

  private String name;
  private int age;
  private String emailId;
  private String mobNo;
}
