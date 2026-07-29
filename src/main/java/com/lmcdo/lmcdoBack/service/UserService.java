package com.lmcdo.lmcdoBack.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Optional<User> getUser(final Long id) {
    return userRepository.findById(id);
  }

  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public boolean deleteUser(final Long id) {
    return userRepository.findById(id).map(user -> {
        // 1. Anonymisation des données pour libérer les contraintes d'unicité
        user.setName("DeletedUser_" + id);
        user.setEmail("deleted_" + id + "@lmcdo.local");
        user.setPassword(""); // On vide le hash du mot de passe

        // 2. Changement de statut et enregistrement de la date
        user.setIsActive(false);
        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
        return true;
    }).orElse(false);
  }

  /**
   * Sauvegarde l'utilisateur en hachant son mot de passe avec Argon2 s'il a été modifié
   */
  public User saveUser(User user) {
    // On hache le mot de passe uniquement s'il n'est pas vide et s'il n'est pas déjà haché
    if (user.getPassword() != null && !user.getPassword().startsWith("$argon2") && !user.getPassword().startsWith("{argon2}")) {
      String encodedPassword = passwordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
    }
    return userRepository.save(user);
  }

  /**
     * Vérifie si le mot de passe en clair correspond au hash Argon2 stocké en base
     */
  public boolean verifyPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
