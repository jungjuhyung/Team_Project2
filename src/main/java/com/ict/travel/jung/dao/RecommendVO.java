package com.ict.travel.jung.dao;

import org.springframework.web.multipart.MultipartFile;

public class RecommendVO {
	String path_post_idx, u_idx, u_id, areacode, main_image, path_post_title, path_post_content, regdate, like;
	String[] contenttypeid;
	MultipartFile f_main;
	
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
	public String[] getContenttypeid() {
		return contenttypeid;
	}
	public void setContenttypeid(String[] contenttypeid) {
		this.contenttypeid = contenttypeid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getMain_image() {
		return main_image;
	}
	public void setMain_image(String main_image) {
		this.main_image = main_image;
	}
	public String getPath_post_title() {
		return path_post_title;
	}
	public void setPath_post_title(String path_post_title) {
		this.path_post_title = path_post_title;
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
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public MultipartFile getF_main() {
		return f_main;
	}
	public void setF_main(MultipartFile f_main) {
		this.f_main = f_main;
	}
}
