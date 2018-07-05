package com.example.david.service;

import com.example.david.dto.Pagination;
import com.example.david.model.SystemParameters;

public interface SystemParametersService{

	public void findAll(Pagination pagination);
	
	public SystemParameters findById(Integer idSystemParameters);
}