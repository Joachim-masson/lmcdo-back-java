package com.lmcdo.lmcdoBack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lmcdo.lmcdoBack.model.User;
import com.lmcdo.lmcdoBack.repository.CharacterRepository;
import com.lmcdo.lmcdoBack.repository.UserRepository;

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

  public void deleteUser(final Long id) {
    UserRepository.deleteById(id);
  }

  public User saveUser(User user) {
    User savedUser = UserRepository.save(user);
    return savedUser;
  }
}
