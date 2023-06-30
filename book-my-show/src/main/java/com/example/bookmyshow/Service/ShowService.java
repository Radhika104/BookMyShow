package com.example.bookmyshow.Service;


import com.example.bookmyshow.Dtos.RequestDtos.AddShowDto;
import com.example.bookmyshow.Dtos.RequestDtos.GetAllShowsForMovieRequestDto;
import com.example.bookmyshow.Dtos.RequestDtos.ShowSeatsDto;
import com.example.bookmyshow.Dtos.ResponseDtos.GetAllShowsForMovieResponseDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Exceptions.MovieNotFoundException;
import com.example.bookmyshow.Exceptions.ShowNotFoundException;
import com.example.bookmyshow.Exceptions.TheaterNotFoundException;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.Repository.MovieRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

  @Autowired
  private ShowRepository showRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private TheaterRepository theaterRepository;

  public String addShow(AddShowDto addShowDto) throws MovieNotFoundException, TheaterNotFoundException
  {
    Show show= ShowTransformer.convertDtoToEntity(addShowDto);
    Optional<Movie> movieOptional=movieRepository.findById(addShowDto.getMovieId());
    if(!movieOptional.isPresent()) throw new MovieNotFoundException("Movie not Found");

    Optional<Theater> theaterOptional=theaterRepository.findById(addShowDto.getTheaterId());
    if(!theaterOptional.isPresent()) throw new TheaterNotFoundException("Theater not Found");

    Movie movie=movieOptional.get();
    Theater theater=theaterOptional.get();

    show.setMovie(movie);
    show.setTheater(theater);

    show=showRepository.save(show);

    movie.getShowList().add(show);
    theater.getShowList().add(show);

     movieRepository.save(movie);
     theaterRepository.save(theater);
     return "Show has been added with show id : "+show.getId();
  }
  public String associateShowSeat(ShowSeatsDto showSeatsDto) throws ShowNotFoundException
  {
    Optional<Show> showOptional=showRepository.findById(showSeatsDto.getShowId());
    if(!showOptional.isPresent()) throw new ShowNotFoundException("Show not Found");

    Show show=showOptional.get();
    Theater theater=show.getTheater();

    List<TheaterSeat> theaterSeatList=theater.getThreaterSeatList();

    for(TheaterSeat theaterSeat:theaterSeatList)
    {
      ShowSeat showSeat=new ShowSeat();

      showSeat.setSeatNo(theaterSeat.getSeatNo());
      showSeat.setSeatType(theaterSeat.getSeatType());
      showSeat.setAvailable(true);
      if(theaterSeat.getSeatType().equals(SeatType.CLASSIC))
          showSeat.setPrice(showSeatsDto.getPriceForClassicSeats());
      else
        showSeat.setPrice(showSeatsDto.getPriceForPremiumSeats());

      showSeat.setFoodAttached(false);
      showSeat.setShow(show);
      show.getShowSeatList().add(showSeat);

    }
    showRepository.save(show);
    //child will automaically saved

    return "Show seats successfully added";
  }
  public List<GetAllShowsForMovieResponseDto> getAllShowsForMovieInTheater(GetAllShowsForMovieRequestDto getAllShowsForMovieRequestDto)
  {
//    String format = "yyyy-MM-dd HH:mm:ss.SSSSSS";
//    Date date=null;
//    try {
//      SimpleDateFormat sdf = new SimpleDateFormat(format);
//      date= sdf.parse(getAllShowsForMovieRequestDto.getDate());
//    } catch (ParseException e) {
//      System.out.println("Error parsing date: " + e.getMessage());
//    }
    System.out.println(getAllShowsForMovieRequestDto.getDate());
     List<Show> showList=showRepository.getAllShows(getAllShowsForMovieRequestDto.getMovieId(),
       getAllShowsForMovieRequestDto.getTheaterId(),
       getAllShowsForMovieRequestDto.getDate()
       );
     List<GetAllShowsForMovieResponseDto> result=new ArrayList<>();
     for(Show show:showList)
     {
       GetAllShowsForMovieResponseDto responseDto=new GetAllShowsForMovieResponseDto();
       responseDto.setShowId(show.getId());
       responseDto.setShowTime(show.getTime());
       responseDto.setDate(show.getDate());
       result.add(responseDto);
     }
     return result;
  }
}
