package com.example.david.service;

import java.util.List;

import com.example.david.model.LevelThree;
import com.example.david.model.ViewLevelThree;

public interface LevelThreeService{
	
	public void save(LevelThree levelThree);
	
	public void save(List<LevelThree> levelThreeList);	
	
	public int findPendingOrFail();
	
	public List<ViewLevelThree> findPendingOrFail(int limit, int limit1);
	
	public int findSuccess();
	
	public LevelThree findOne(Integer id);
}