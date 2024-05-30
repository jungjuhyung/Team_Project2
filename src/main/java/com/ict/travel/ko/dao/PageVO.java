package com.ict.travel.ko.dao;

public class PageVO {
	private String search;
	private int offset, limit;
	
	public PageVO(String search, int offset, int limit) {
		this.search = search;
		this.offset = offset;
		this.limit = limit;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
