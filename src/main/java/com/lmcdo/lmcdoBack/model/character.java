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
@Table(name = "characters") 

public class Character {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  @Column(name = "idcharacter")
  private Long id; 

  private String name; 

  private Integer age; 

  private String origin; 

  private String picture; 

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "created_date")
  private String createdDate;
}
