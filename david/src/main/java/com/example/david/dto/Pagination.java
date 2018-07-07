package com.example.david.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.david.model.LogError;
import com.example.david.model.SystemParameters;

public class Pagination {
	
	private LogError logError;
	private SystemParameters systemParameters;
	
	private List<LogError> logErrorList;
	private List<SystemParameters> systemParametersList;
	
	private Pager pager;
	
	public Pagination() {
		logError = new LogError();
		systemParameters = new SystemParameters();
		logErrorList = new ArrayList<>();
		pager = new Pager();
	}
	
	public void getPage(long querySize, Long pageSize) {
		this.getPager().setPaginaton(pageSize);
		long length = querySize;
    	long page = this.getPager().getPage();
    	long size = (long) Math.ceil((double)length/this.getPager().getPaginaton());
    	
    	page = (page == 0 || page > size) ? 1 : page;
    	
    	this.getPager().setSize(size);
    	this.getPager().setPage(size == 0 ? 0 : page);
    	this.getPager().setLength(length);
	}

	public LogError getLogError() {
		return logError;
	}

	public void setLogError(LogError logError) {
		this.logError = logError;
	}
	
	public SystemParameters getSystemParameters() {
		return systemParameters;
	}

	public void setSystemParameters(SystemParameters systemParameters) {
		this.systemParameters = systemParameters;
	}

	public List<LogError> getLogErrorList() {
		return logErrorList;
	}

	public void setLogErrorList(List<LogError> logErrorList) {
		this.logErrorList = logErrorList;
	}

	public List<SystemParameters> getSystemParametersList() {
		return systemParametersList;
	}

	public void setSystemParametersList(List<SystemParameters> systemParametersList) {
		this.systemParametersList = systemParametersList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
