package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="theaterSeat")
@NoArgsConstructor
public class TheaterSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String seatNo;
  @Enumerated(EnumType.STRING)
  private SeatType seatType;

  //mapping with theater (parent class)
  @ManyToOne
  @JoinColumn
  private Theater theater;
}
