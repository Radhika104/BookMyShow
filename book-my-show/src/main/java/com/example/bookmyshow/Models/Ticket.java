package com.example.bookmyshow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name="ticket")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String bookedSeats;
  private String seatsHavingRefreshments;
  private int totalTicketPrice;

  @CreationTimestamp
  private Date bookedAt;

  @ManyToOne
  @JoinColumn
  private Show show;

  @ManyToOne
  @JoinColumn
  private User user;
}
