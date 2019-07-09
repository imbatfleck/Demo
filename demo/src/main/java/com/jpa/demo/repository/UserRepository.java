package com.jpa.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.jpa.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
