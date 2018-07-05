package com.example.david.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);
    
    List<User> findAll();
}