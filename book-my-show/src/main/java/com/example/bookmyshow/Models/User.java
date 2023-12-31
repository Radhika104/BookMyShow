package com.example.bookmyshow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data //contains setter, getter, RequiredArgsConstructor, toString only
@NoArgsConstructor
@AllArgsConstructor
@Builder //required both AllArgsContructor and NoArgsContructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private int age;
  private String mobNo;
  private String emailId;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  private List<Ticket> ticketList=new ArrayList<>();
}
