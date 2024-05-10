package com.ict.travel.cho.dao;

public class PathPostVO {
	private String path_post_idx, u_idx, u_id, firstimage, title, path_post_content, regdate, heart, u_heart, areacode,contenttypeid;

	public String getHeart() {
		return heart;
	}

	public String getU_heart() {
		return u_heart;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(String contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public void setU_heart(String u_heart) {
		this.u_heart = u_heart;
	}

	public void setHeart(String heart) {
		this.heart = heart;
	}

	public String getPath_post_idx() {
		return path_post_idx;
	}

	public void setPath_post_idx(String path_post_idx) {
		this.path_post_idx = path_post_idx;
	}

	public String getU_idx() {
		return u_idx;
	}

	public void setU_idx(String u_idx) {
		this.u_idx = u_idx;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getPath_post_content() {
		return path_post_content;
	}

	public void setPath_post_content(String path_post_content) {
		this.path_post_content = path_post_content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
}
