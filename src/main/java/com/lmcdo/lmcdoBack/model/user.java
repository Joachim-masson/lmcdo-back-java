package com.lmcdo.lmcdoBack.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 

import lombok.Data; 

@Data 
@Entity 
@Table(name = "user") 

public class User {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  @Column(name="iduser") 
  private Long id; 

  @Column(name="username") 
  private String name; 

  @Column(name="email") 
  private String email; 

  @Column(name = "password_hash", nullable = false) 
  private String password;

  @Column(name="user_role") 
  private String userRole;
  
  @Column(name="is_active") 
  private Boolean isActive;

  @Column(name = "last_login_at")
  private LocalDateTime lastLoginAt;

  @Column(name = "email_verified_at")
  private LocalDateTime emailVerifiedAt;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Column(name = "banned_at")
  private LocalDateTime bannedAt;
}
