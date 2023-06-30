package com.example.bookmyshow.Controller;

import com.example.bookmyshow.Dtos.RequestDtos.AddUserDto;
import com.example.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.example.bookmyshow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/addUser")
  public ResponseEntity addUser(@RequestBody AddUserDto addUserDto)
  {
         try
         {
           return new ResponseEntity(userService.addUser(addUserDto),HttpStatus.OK);
         }catch(Exception e)
         {
           return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
         }
  }


    @GetMapping("/getUserByEmailId/{emailId}")
    public UserResponseDto getUserByEmailId(@PathVariable("emailId") String emailId)
    {
      try
      {
       UserResponseDto userResponseDto=userService.getUserByEmailId(emailId);

       userResponseDto.setStatusCode("200");
       userResponseDto.setStatusMessage("SUCCESS");
       return userResponseDto;
      }catch(Exception e)
      {
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setStatusCode("500");
        userResponseDto.setStatusMessage("FAILURE");
        return userResponseDto;
      }
    }

    @GetMapping("/getOlderUser")
    public UserResponseDto getOlderUser()
    {
      try
      {
        UserResponseDto userResponseDto=userService.getOldestUser();
        userResponseDto.setStatusCode("200");
        userResponseDto.setStatusMessage("SUCCESS");
        return userResponseDto;
      }catch(Exception e)
      {
         UserResponseDto userResponseDto=new UserResponseDto();
         userResponseDto.setStatusCode("500");
         userResponseDto.setStatusMessage("FAILURE");
         return userResponseDto;
      }
    }
}
