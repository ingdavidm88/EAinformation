package com.example.david.service;

import java.util.List;

import com.example.david.model.Menu;

public interface MenuService{

	public List<Menu> menuList(Integer idParentMenu, Integer idRole);
	
	public Menu findByUrlName(String urlName);
}