package com.lmcdo.lmcdoBack.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.lmcdo.lmcdoBack.model.User;

// @Repository
public interface UserRepository extends CrudRepository <User, Long>{
  Optional<User> findByName(String name);
}


