package com.example.david.dto;

import java.io.Serializable;
import java.util.List;

import com.example.david.model.ParentMenu;

public class TransactionPage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String userName;
	
	private String title;
	
	private Integer idRole;
	
	private List<ParentMenu> parentMenuList;
	
	private String token;
	
	private String headerName;	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	public List<ParentMenu> getParentMenuList() {
		return parentMenuList;
	}

	public void setParentMenuList(List<ParentMenu> parentMenuList) {
		this.parentMenuList = parentMenuList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
}
