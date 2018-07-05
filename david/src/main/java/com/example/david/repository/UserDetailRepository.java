package com.example.david.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.david.model.UserDetail;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetail, Integer> {

}