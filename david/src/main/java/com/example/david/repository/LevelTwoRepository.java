package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.LevelTwo;

@Repository
public interface LevelTwoRepository extends CrudRepository<LevelTwo, Integer> {

}