package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.RequestDtos.AddUserDto;
import com.example.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.example.bookmyshow.Exceptions.UserNotFoundException;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public String addUser(AddUserDto addUserDto)
  {
           User user= UserTransformer.convertDtoToEntity(addUserDto);

           userRepository.save(user);

           return "User added Successfully";
  }
  public UserResponseDto getUserByEmailId(String emailId) throws UserNotFoundException
  {
        User user=userRepository.findByEmailId(emailId);
        if(user==null) throw new UserNotFoundException("User not exists with emailId");
        UserResponseDto userResponseDto=UserTransformer.convertEntityToDto(user);

        return userResponseDto;
  }
  public UserResponseDto getOldestUser() throws UserNotFoundException
  {
    List<User> userList=userRepository.findAll();

    int maxAge=Integer.MIN_VALUE;
    User user=null;
    for(User user1:userList)
    {
      if(user1.getAge()>maxAge)
      {
        maxAge= user1.getAge();
        user=user1;
      }
    }
    if(user==null) throw new UserNotFoundException("User not Found");
    UserResponseDto userResponseDto=UserTransformer.convertEntityToDto(user);
    return userResponseDto;
  }
}
