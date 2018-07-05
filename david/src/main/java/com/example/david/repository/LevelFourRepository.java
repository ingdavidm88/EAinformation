package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.LevelFour;

@Repository
public interface LevelFourRepository extends CrudRepository<LevelFour, Integer> {

}