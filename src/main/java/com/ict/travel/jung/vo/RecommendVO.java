package com.ict.travel.jung.vo;

import org.springframework.web.multipart.MultipartFile;

public class RecommendVO {
	private String path_post_idx, u_idx, u_id, r_areacode,firstimage, path_post_title, path_post_content, regdate, r_contenttypeid, heart,img_status;
	public String getImg_status() {
		return img_status;
	}
	public void setImg_status(String img_status) {
		this.img_status = img_status;
	}
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

	public MultipartFile getF_main() {
		return f_main;
	}
	public void setF_main(MultipartFile f_main) {
		this.f_main = f_main;
	}
	public String getHeart() {
		return heart;
	}
	public void setHeart(String heart) {
		this.heart = heart;
	}
	public String getPath_post_title() {
		return path_post_title;
	}
	public void setPath_post_title(String path_post_title) {
		this.path_post_title = path_post_title;
	}
		
	
}
