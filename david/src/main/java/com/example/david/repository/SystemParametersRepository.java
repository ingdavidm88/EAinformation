package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.SystemParameters;

@Repository
public interface SystemParametersRepository extends CrudRepository<SystemParameters, Integer> {

}