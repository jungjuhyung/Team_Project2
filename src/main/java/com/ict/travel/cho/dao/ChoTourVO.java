package com.ict.travel.cho.dao;

public class ChoTourVO {
	private String contentid, contenttypeid, title, firstimage, heart, uheart;

	public String getHeart() {
		return heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getContentid() {
		return contentid;
	}

	public String getUheart() {
		return uheart;
	}

	public void setUheart(String uheart) {
		this.uheart = uheart;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(String contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	
}
