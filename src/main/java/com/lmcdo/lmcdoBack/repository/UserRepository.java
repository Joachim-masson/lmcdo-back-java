package com.lmcdo.lmcdoBack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lmcdo.lmcdoBack.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{

}
