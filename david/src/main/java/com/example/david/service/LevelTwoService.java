package com.example.david.service;

import java.util.List;

import com.example.david.model.LevelTwo;
import com.example.david.model.ViewLevelTwo;

public interface LevelTwoService{
	
	public void save(LevelTwo levelTwo);
	
	public void save(List<LevelTwo> levelTwoList);	
	
	public int findPendingOrFail();
	
	public List<ViewLevelTwo> findPendingOrFail(int limit, int limit1);
	
	public int findSuccess();
	
	public LevelTwo findOne(Integer id);
}