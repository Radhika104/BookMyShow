package com.example.bookmyshow.Transformers;

import com.example.bookmyshow.Dtos.RequestDtos.AddUserDto;
import com.example.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.example.bookmyshow.Models.User;
import org.springframework.beans.factory.config.CustomScopeConfigurer;

public class UserTransformer {

  public static User convertDtoToEntity(AddUserDto userDto)
  {
         /*before builder annotation
         User user=new User();
         user.setName(userDto.getName());
         user.setAge(userDto.getAge());
         user.setEmailId(userDto.getEmailId());
         user.setMobNo(userDto.getMobNo());*/

       //using builder Annotation
      User user=User.builder().name(userDto.getName()).age(userDto.getAge()).emailId(userDto.getEmailId()).
                    mobNo(userDto.getMobNo()).build();

      return user;
  }
  public static UserResponseDto convertEntityToDto(User user)
  {
      UserResponseDto userResponseDto=UserResponseDto.builder()
        .age(user.getAge()).emailId(user.getEmailId()).mobNo(user.getMobNo())
        .name(user.getName()).build();
      return userResponseDto;
  }
}
