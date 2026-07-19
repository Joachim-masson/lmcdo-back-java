package com.lmcdo.lmcdoBack.controller;

import java.time.LocalDateTime;
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

import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.service.UserService;

@RestController // permet d’indiquer à Spring que cette classe est un bean.Elle indique à Spring d’insérer le retour de la méthode au format JSON dans le corps de la réponse HTTP

public class UserController {
  @Autowired
  private UserService userService;

    /**
	 * Create - Add a new user
	 * @param user An object user
	 * @return The user object saved
	 */
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}


  /**
	 * Read - Get one user 
	 * @param id The id of the user
	 * @return An User object full filled
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Long id) {
		Optional<User> user = userService.getUser(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}


    /**
    * Read - Get all users
    * @return - An Iterable object of User full filled
    */
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }


    /**
	 * Update - Update an existing user
	 * @param id - The id of the user to update
	 * @param user - The user object updated
	 * @return
	 */
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
		Optional<User> e = userService.getUser(id);
		if(e.isPresent()) {
			User currentUser = e.get();
			boolean hasChanged = false; // Flag facultatif mais propre pour savoir s'il y a eu des modifs

			String name = user.getName();
			if(name != null) {
				currentUser.setName(name);
        hasChanged = true;
			}
			String userRole = user.getUserRole();
			if(userRole != null) {
				currentUser.setUserRole(userRole);
        hasChanged = true;
			}
			Boolean isActive = user.getIsActive(); // Utilise bien l'objet Boolean (Majuscule) dans ton modèle User
        if (isActive != null) {
            // Si le statut change réellement
            if (currentUser.getIsActive() != isActive) {
                currentUser.setIsActive(isActive);
                hasChanged = true;
                
                // Si on désactive l'utilisateur -> On enregistre la date du ban
                if (!isActive) {
                    currentUser.setBannedAt(LocalDateTime.now());
                } else {
                    // Si on le réactive -> On retire la date de ban
                    currentUser.setBannedAt(null);
                }
            }
        }
		
			if (hasChanged) {
            currentUser.setUpdatedAt(LocalDateTime.now()); // Assure-toi d'avoir le setter correspondant
            userService.saveUser(currentUser);
        }
			return currentUser;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete an user
	 * @param id - The id of the user to delete
	 */
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final Long id) {
	  userService.deleteUser(id);
	}

	@PostMapping("/login")
	public User loginUser(@RequestBody User loginData) {
    // 1. Chercher tous les utilisateurs et filtrer par email (Mieux vaut créer une méthode findByEmail dans le Repository)
    Iterable<User> users = userService.getUsers();
    
    for (User user : users) {
        if (user.getEmail().equalsIgnoreCase(loginData.getEmail())) {
    // 2. Vérification du mot de passe et si le compte est actif
            if (user.getPassword().equals(loginData.getPassword()) && Boolean.TRUE.equals(user.getIsActive())) {
                user.setLastLoginAt(LocalDateTime.now());
                userService.saveUser(user); // Met à jour la date de dernière connexion
                return user; 
            }
        }
    }
    // 3. Si aucun utilisateur trouvé ou mauvais mot de passe
    throw new RuntimeException("Authentification échouée : email/mot de passe incorrect ou compte banni.");
	}
	
}
