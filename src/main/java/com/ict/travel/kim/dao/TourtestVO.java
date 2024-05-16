package com.ict.travel.kim.dao;

import java.util.ArrayList;
import java.util.List;

public class TourtestVO {
	private String path_marker_idx, contentid, path_post_idx, regdate, areacode, sigungucode, contenttypeid, title;
	
	private double mapy, mapx;
	
	public List<TourtestVO> getImgList() {
		return imgList;
	}
	public void setImgList(List<TourtestVO> imgList) {
		this.imgList = imgList;
	}

	private String img_idx, image_name ;
	List<TourtestVO> imgList;
	
	public void addImage(TourtestVO img) {
        if (imgList == null) {
            imgList = new ArrayList<>();
        }
        imgList.add(img);
    }
	public String getPath_marker_idx() {
		return path_marker_idx;
	}

	public String getImg_idx() {
		return img_idx;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public void setImg_idx(String img_idx) {
		this.img_idx = img_idx;
	}

	

	public void setPath_marker_idx(String path_marker_idx) {
		this.path_marker_idx = path_marker_idx;
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getPath_post_idx() {
		return path_post_idx;
	}

	public void setPath_post_idx(String path_post_idx) {
		this.path_post_idx = path_post_idx;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getSigungucode() {
		return sigungucode;
	}

	public void setSigungucode(String sigungucode) {
		this.sigungucode = sigungucode;
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

	public double getMapy() {
		return mapy;
	}

	public void setMapy(double mapy) {
		this.mapy = mapy;
	}

	public double getMapx() {
		return mapx;
	}

	public void setMapx(double mapx) {
		this.mapx = mapx;
	}
	
	
	

	
	
}
