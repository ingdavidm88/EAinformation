package com.example.david.service;

import java.util.List;

import com.example.david.model.LevelOne;

public interface LevelOneService{

	public void save(LevelOne levelOne);	
	
	public List<LevelOne> findPendingOrFail();
	
	public int findSuccess();
}