package com.lmcdo.lmcdoBack.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmcdo.lmcdoBack.model.Character;
import com.lmcdo.lmcdoBack.repository.CharacterRepository;

import lombok.Data;

@Data
@Service
public class CharacterService {
  @Autowired
  private CharacterRepository CharacterRepository;

  public Optional<Character> getCharacter(final Long id) {
    return CharacterRepository.findById(id);
  }

  public Iterable<Character> getCharacters() {
    return CharacterRepository.findAll();
  }

  public void deleteCharacter(final Long id) {
    CharacterRepository.deleteById(id);
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

      // 2. Attribution de l'utilisateur créateur factice (en attendant l'Auth)
      if (character.getCreatedBy() == null) {
        // TODO: Remplace par l'utilisateur extrait du SecurityContext plus tard
        character.setCreatedBy("9"); 
      }
    }

    // Sauvegarde JPA
    return CharacterRepository.save(character);
  }
}
