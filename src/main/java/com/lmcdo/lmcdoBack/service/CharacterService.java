package com.lmcdo.lmcdoBack.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmcdo.lmcdoBack.model.Character;
import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.repository.CharacterRepository;
import com.lmcdo.lmcdoBack.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class CharacterService {

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<Character> getCharacter(final Long id) {
    return characterRepository.findById(id);
  }

  public Iterable<Character> getCharacters() {
    return characterRepository.findAll();
  }

  public void deleteCharacter(final Long id) {
    characterRepository.deleteById(id);
  }

  /**
   * Sauvegarde ou met à jour un personnage en gérant la logique métier.
   */
  public Character saveCharacter(Character character) {
    // Validation / Traitement lors de la création (ID nul)
    if (character.getId() == null) {
          
      // 1. Génération de la date courante (au format ISO / Lisible)
      if (character.getCreatedDate() == null) {
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        character.setCreatedDate(currentDate);
      }

      // 2. Attribution de l'utilisateur créateur
      if (character.getCreatedBy() == null) {
        User currentUser = getCurrentUser();
        character.setCreatedBy(currentUser); 
      }
    }

    // Sauvegarde JPA
    return characterRepository.save(character);
  }

  /**
   * Récupère l'utilisateur en BDD (ID 1 par défaut).
   * ⚠️ Assure-toi qu'un utilisateur avec l'id 1 existe bien dans ta table 'user'.
   */
  private User getCurrentUser() {
    // On charge l'utilisateur ayant l'ID 1L (change la valeur si besoin)
    return userRepository.findById(1L)
        .orElseThrow(() -> new RuntimeException("Utilisateur introuvable dans la base de données."));
  }
}