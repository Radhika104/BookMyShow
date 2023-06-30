package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

@Entity
@Table(name="show_seat")
@Data
public class ShowSeat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String seatNo;

  @Enumerated(EnumType.STRING)
  private SeatType seatType;

  private int price;
  @Column(columnDefinition= "BOOLEAN")
  private boolean isAvailable;

  @Column(columnDefinition = "BOOLEAN")
  private boolean isFoodAttached;

  @ManyToOne
  @JoinColumn
  private Show show;

}
