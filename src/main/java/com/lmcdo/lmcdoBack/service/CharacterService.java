package com.lmcdo.lmcdoBack.service;

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

  public Character saveCharacter(Character character) {
    Character savedCharacter = CharacterRepository.save(character);
    return savedCharacter;
  }
}
