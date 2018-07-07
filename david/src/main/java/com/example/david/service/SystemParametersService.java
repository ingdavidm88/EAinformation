package com.example.david.service;

import com.example.david.dto.Pagination;
import com.example.david.model.SystemParameters;

public interface SystemParametersService{

	public void findAll(Pagination pagination, Long pageSize);
	
	public SystemParameters findById(Integer idSystemParameters);
	
	public SystemParameters save(SystemParameters systemParameters);
}