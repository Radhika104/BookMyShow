package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.RequestDtos.TicketRequestDto;
import com.example.bookmyshow.Dtos.ResponseDtos.TicketResponseDto;
import com.example.bookmyshow.Dtos.ResponseDtos.UserResponseDto;
import com.example.bookmyshow.Exceptions.SeatsAreNotAvailableException;
import com.example.bookmyshow.Exceptions.ShowNotFoundException;
import com.example.bookmyshow.Exceptions.UserNotFoundException;
import com.example.bookmyshow.Models.Show;
import com.example.bookmyshow.Models.ShowSeat;
import com.example.bookmyshow.Models.Ticket;
import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TicketRepository;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private ShowRepository showRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JavaMailSender emailSender;

  public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws UserNotFoundException, ShowNotFoundException, SeatsAreNotAvailableException
  {
    Optional<User> optionalUser=userRepository.findById(ticketRequestDto.getUserId());
    if(!optionalUser.isPresent()) throw new UserNotFoundException("User not Found");

    Optional<Show> optionalShow=showRepository.findById(ticketRequestDto.getShowId());
    if(!optionalUser.isPresent()) throw new ShowNotFoundException("Show not Found");

    //one more check to check if list of refreshments seats contains in required seats

    Show show=optionalShow.get();
    User user=optionalUser.get();
    //1. check if seats are available or not using function
    boolean isAvailable=validateSeatAvailability(show,ticketRequestDto.getRequestedSeats());

    if(!isAvailable) throw new SeatsAreNotAvailableException("Required Seats are not available");

    //2. calculate Price
    int price=calculateTotalPrice(show,ticketRequestDto);

    //convert list of requiredSeats and refreshmentsAttachedSeats to String
    String seats= TicketTransformer.convertListToString(ticketRequestDto.getRequestedSeats());
    String refreshmentsSeats=TicketTransformer.convertListToString(ticketRequestDto.getAddRefreshmentsToSeats());

    //3. Create Ticket Entity
    Ticket ticket=new Ticket();
    ticket.setTotalTicketPrice(price);
    ticket.setShow(show);
    ticket.setUser(user);
    ticket.setBookedSeats(seats);
    ticket.setSeatsHavingRefreshments(refreshmentsSeats);

    ticket=ticketRepository.save(ticket);

    //bidirectional mapping
     user.getTicketList().add(ticket);
     show.getTicketList().add(ticket);

     userRepository.save(user);
     showRepository.save(show);

     //simple mail message
      SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

      String body="Hi "+user.getName()+" ! \n"+
              "You have successfully booked a ticket. Please find the following details \n"+
              "Booked seat No's : "+seats+"\n"+
              "Movie name : "+show.getMovie().getName()+"\n"+
              "Show Date is : "+show.getDate()+"\n"+
              "Show Time : "+show.getTime()+"\n"+
              "Enjoy the Show !!!";

      simpleMailMessage.setSubject("Ticket Confirmation Mail");
      simpleMailMessage.setFrom("a3929472@gmail.com");
      simpleMailMessage.setText(body);
      simpleMailMessage.setTo(user.getEmailId());

      emailSender.send(simpleMailMessage);

     //convert ticket to responseTicketDto
    TicketResponseDto ticketResponseDto=TicketTransformer.convertEntityToDto(ticket);
   return ticketResponseDto;
  }
  private boolean validateSeatAvailability(Show show,List<String> requiredSeats)
  {
    List<ShowSeat> showSeatList=show.getShowSeatList();
    for(ShowSeat showSeat:showSeatList)
    {
      if(requiredSeats.contains(showSeat.getSeatNo()) && !showSeat.isAvailable()) return false;
    }
    return true;
  }
  private int calculateTotalPrice(Show show,TicketRequestDto ticketRequestDto)
  {
    List<ShowSeat> showSeatList=show.getShowSeatList();
    int price=0;
    for(ShowSeat showSeat:showSeatList)
    {
      String seatNo=showSeat.getSeatNo();
       if(ticketRequestDto.getRequestedSeats().contains(seatNo))
       {
         price+=showSeat.getPrice();
         if(ticketRequestDto.getAddRefreshmentsToSeats().contains(seatNo))
         {
           price+=100;
           showSeat.setFoodAttached(true);
         }
         showSeat.setAvailable(false);
       }
    }
    return price;
  }
}
