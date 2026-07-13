package com.lmcdo.lmcdoBack.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lmcdo.lmcdoBack.model.Character;
import com.lmcdo.lmcdoBack.service.CharacterService;

@RestController // permet d’indiquer à Spring que cette classe est un bean.Elle indique à Spring d’insérer le retour de la méthode au format JSON dans le corps de la réponse HTTP
@CrossOrigin(origins = "http://localhost:5173")
public class CharacterController {
  @Autowired
  private CharacterService characterService;

    /**
	 * Create - Add a new character
	 * @param character An object character
	 * @return The character object saved
	 */
	@PostMapping("/character")
	public Character createCharacter(@RequestBody Character character) {
		return characterService.saveCharacter(character);
	}


  /**
	 * Read - Get one character 
	 * @param id The id of the character
	 * @return An Character object full filled
	 */
	@GetMapping("/character/{id}")
	public Character getCharacter(@PathVariable("id") final Long id) {
		Optional<Character> character = characterService.getCharacter(id);
		if(character.isPresent()) {
			return character.get();
		} else {
			return null;
		}
	}


    /**
    * Read - Get all employees
    * @return - An Iterable object of Character full filled
    */
    @GetMapping("/characters")
    public Iterable<Character> getCharacters() {
        return characterService.getCharacters();
    }


    /**
	 * Update - Update an existing character
	 * @param id - The id of the character to update
	 * @param character - The character object updated
	 * @return
	 */
	@PutMapping("/character/{id}")
	public Character updateEmployee(@PathVariable("id") final Long id, @RequestBody Character character) {
		Optional<Character> e = characterService.getCharacter(id);
		if(e.isPresent()) {
			Character currentCharacter = e.get();
			
			String name = character.getName();
			if(name != null) {
				currentCharacter.setName(name);
			}
			int age = character.getAge();
			if(age != 0) {
				currentCharacter.setAge(age);;
			}
			String origin = character.getOrigin();
			if(origin != null) {
				currentCharacter.setOrigin(origin);
			}
			String picture = character.getPicture();
			if(picture != null) {
				currentCharacter.setPicture(picture);;
			}
			characterService.saveCharacter(currentCharacter);
			return currentCharacter;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an character
	 * @param id - The id of the character to delete
	 */
	@DeleteMapping("/character/{id}")
	public void deleteCharacter(@PathVariable("id") final Long id) {
	  characterService.deleteCharacter(id);
	}
	
}
