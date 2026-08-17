package com.lmcdo.lmcdoBack.repository;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;

import com.lmcdo.lmcdoBack.model.Character;

// @Repository 
public interface CharacterRepository extends CrudRepository <Character, Long>{

}
