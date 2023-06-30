package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false,unique = true)
  private String name;

  private double duration;

  private Date releaseDate;

  @Column(scale=2)
  private double rating;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  @Enumerated(EnumType.STRING)
  private Language language;

  @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
  private List<Show> showList=new ArrayList<>();
}
