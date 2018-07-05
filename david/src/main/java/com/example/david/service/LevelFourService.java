package com.example.david.service;

import java.util.List;

import com.example.david.model.LevelFour;
import com.example.david.model.ViewLevelFour;

public interface LevelFourService{

	public void save(LevelFour levelFour);
	
	public int findPendingOrFail();
	
	public List<ViewLevelFour> findPendingOrFail(int limit, int limit1);
	
	public int findSuccess();
	
	public LevelFour findOne(Integer id);
}