package com.example.david.service;

import java.util.List;

import com.example.david.model.ParentMenu;

public interface ParentMenuService{

	public List<ParentMenu> parentMenuList(Integer idRole);
}