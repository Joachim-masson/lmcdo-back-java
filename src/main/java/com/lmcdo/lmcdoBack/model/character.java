package com.lmcdo.lmcdoBack.model;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 

import lombok.Data; 

@Data 
@Entity 
@Table(name = "character") 

public class Character {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long id; 

  private String name; 

  private int age; 

  private String origin; 

  private String picture; 

  private String created_by;

  private String created_date;
}
