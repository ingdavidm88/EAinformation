package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.LevelThree;

@Repository
public interface LevelThreeRepository extends CrudRepository<LevelThree, Integer> {
	
}