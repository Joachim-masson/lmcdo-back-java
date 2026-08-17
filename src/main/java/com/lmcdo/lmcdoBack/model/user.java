package com.lmcdo.lmcdoBack.model;

import java.time.LocalDateTime;
import java.util.HashSet;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table; 

import java.util.HashSet;
import java.util.Set;

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

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "role")
  private Set<String> roles = new HashSet<>();

  // Getters & Setters
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }


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
