package com.example.david.service;

import com.example.david.model.UserDetail;

public interface UserDetailService{

	public UserDetail findByUserDetail(Integer idUserDetail);
	
	public UserDetail saveUserDetail(UserDetail userDetail);
}