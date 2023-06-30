package com.example.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="theater")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(unique = true)
  private String location;

  //mapping in parent class for child class
  @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
  private List<TheaterSeat> threaterSeatList=new ArrayList<>();

  @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
  private List<Show> showList=new ArrayList<>();
}