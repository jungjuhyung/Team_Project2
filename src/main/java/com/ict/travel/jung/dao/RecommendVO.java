package com.ict.travel.jung.dao;

import org.springframework.web.multipart.MultipartFile;

public class RecommendVO {
	private String path_post_idx, u_idx, u_id, r_areacode,firstimage, title, path_post_content, regdate, r_contenttypeid, like;
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
	public String getR_areacode() {
		return r_areacode;
	}
	public void setR_areacode(String r_areacode) {
		this.r_areacode = r_areacode;
	}
	public String getFirstimage() {
		return firstimage;
	}
	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
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
	public String getR_contenttypeid() {
		return r_contenttypeid;
	}
	public void setR_contenttypeid(String r_contenttypeid) {
		this.r_contenttypeid = r_contenttypeid;
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
