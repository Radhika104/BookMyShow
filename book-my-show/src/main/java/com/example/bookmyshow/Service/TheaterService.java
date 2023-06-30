package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.RequestDtos.AddTheaterDto;
import com.example.bookmyshow.Dtos.RequestDtos.TheaterSeatEntryDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.Theater;
import com.example.bookmyshow.Models.TheaterSeat;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {


  @Autowired
  private TheaterRepository theaterRepository;

  public String addTheater(AddTheaterDto addTheaterDto)
  {
         Theater theater= TheaterTransformer.convertDtoToEntity(addTheaterDto);

         theaterRepository.save(theater);
         return "Theater added Successfully";
  }

  public String addTheaterSeats(TheaterSeatEntryDto theaterSeatEntryDto)
  {

          int column= theaterSeatEntryDto.getNoOfSeatAtOneRow();
          int noOfClassicSeats= theaterSeatEntryDto.getNoOfClassicSeats();
          int noOfPremiumSeats= theaterSeatEntryDto.getNoOfPremiumSeats();

          String location=theaterSeatEntryDto.getLocation();
          Theater theater=theaterRepository.findByLocation(location);
          List<TheaterSeat> theaterSeatList=theater.getThreaterSeatList();
          char ch='A';int i=1;

          for(int count=1;count<=noOfClassicSeats;count++)
          {
             if((ch-'A')==column)
             {
               i++;
               ch='A';
             }
            //entries of child class
            TheaterSeat theaterSeat=new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setSeatNo(i+""+ch+"");
            ch++;
            //entry in parent class
            theaterSeatList.add(theaterSeat);
          }
          //for premium seats

     for(int count=1;count<=noOfPremiumSeats;count++)
    {
      if((ch-'A')==column)
      {
        i++;
        ch='A';
      }
      //entries of child class
      TheaterSeat theaterSeat=new TheaterSeat();
      theaterSeat.setTheater(theater);
      theaterSeat.setSeatType(SeatType.PREMIUM);
      theaterSeat.setSeatNo(i+""+ch+"");
      ch++;
      //entry in parent class
      theaterSeatList.add(theaterSeat);
    }

          //saving to parentRepo child will automatically getSaved
          theaterRepository.save(theater);
          return "Theater Seats added Successfully";
  }
}
