package com.example.bookmyshow.Models;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="shows")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Show {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private LocalTime time;
  private Date date;

  @ManyToOne
  @JoinColumn
  private Movie movie;

  @ManyToOne
  @JoinColumn
  private Theater theater;

  @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
  private List<ShowSeat> showSeatList=new ArrayList<>();

  @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
  private List<Ticket> ticketList=new ArrayList<>();

}
