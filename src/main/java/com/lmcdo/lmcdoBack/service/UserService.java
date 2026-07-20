package com.lmcdo.lmcdoBack.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Service
public class UserService {
  @Autowired
  private UserRepository UserRepository;

  public Optional<User> getUser(final Long id) {
    return UserRepository.findById(id);
  }

  public Iterable<User> getUsers() {
    return UserRepository.findAll();
  }

  @Transactional
  public boolean deleteUser(final Long id) {
    return UserRepository.findById(id).map(user -> {
        // 1. Anonymisation des données pour libérer les contraintes d'unicité
        user.setName("DeletedUser_" + id);
        user.setEmail("deleted_" + id + "@lmcdo.local");
        user.setPassword(""); // On vide le hash du mot de passe

        // 2. Changement de statut et enregistrement de la date
        user.setIsActive(false);
        user.setDeletedAt(LocalDateTime.now());

        UserRepository.save(user);
        return true;
    }).orElse(false);
  }

  public User saveUser(User user) {
    User savedUser = UserRepository.save(user);
    return savedUser;
  }
}
