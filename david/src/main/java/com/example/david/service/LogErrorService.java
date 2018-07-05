package com.example.david.service;

import com.example.david.dto.MessageResponse;
import com.example.david.dto.Pagination;
import com.example.david.model.LogError;

public interface LogErrorService{

	public void findAll(Pagination pagination);
	
	public MessageResponse save(LogError logError);
}