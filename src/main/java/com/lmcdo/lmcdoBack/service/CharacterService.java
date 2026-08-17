package com.lmcdo.lmcdoBack.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lmcdo.lmcdoBack.model.Character;
import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.repository.CharacterRepository;
import com.lmcdo.lmcdoBack.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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

   // 2. Si un utilisateur est passé dans le payload avec son ID
    if (character.getCreatedBy() != null && character.getCreatedBy().getId() != null) {
      User existingUser = userRepository.findById(character.getCreatedBy().getId())
          .orElseThrow(() -> new RuntimeException("Utilisateur créateur introuvable."));
      character.setCreatedBy(existingUser);
    }
    }

    // Sauvegarde JPA
    return characterRepository.save(character);
  }

  /**
   * Récupère l'utilisateur en BDD (ID 1 par défaut).
   * ⚠️ Assure-toi qu'un utilisateur avec l'id 1 existe bien dans ta table 'user'.
   */
  private User getCurrentUser() {Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("Debug Auth -> " + authentication);
    if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
        throw new RuntimeException("Aucun utilisateur connecté trouvé.");
    }

    String currentUsername;

    // 2. Extraction du login / username / email
    if (authentication.getPrincipal() instanceof UserDetails) {
        currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();
    } else {
        currentUsername = authentication.getPrincipal().toString();
    }

    // 3. Récupération en BDD via le username (ou findByEmail selon votre entité)
    return userRepository.findByName(currentUsername) // ou findByEmail(currentUsername)
        .orElseThrow(() -> new RuntimeException("Utilisateur connecté introuvable : " + currentUsername));
  }
  
}