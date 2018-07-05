package com.example.david.dto;

public class Pager {
	
	private long size;
	
	private long page;
	
	private long length;
	
	private long paginaton;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public long getPaginaton() {
		return paginaton;
	}

	public void setPaginaton(long paginaton) {
		this.paginaton = paginaton;
	}
}
