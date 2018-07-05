package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.LogError;

@Repository
public interface LogErrorRepository extends CrudRepository<LogError, Integer> {

}