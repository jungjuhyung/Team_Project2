package com.ict.travel.jung.dao;

public class MarkerVO {
	private String[] title, contentid;
	private double[] mapx, mapy;
	public String[] getTitle() {
		return title;
	}
	public void setTitle(String[] title) {
		this.title = title;
	}
	public String[] getContentid() {
		return contentid;
	}
	public void setContentid(String[] contentid) {
		this.contentid = contentid;
	}
	public double[] getMapx() {
		return mapx;
	}
	public void setMapx(double[] mapx) {
		this.mapx = mapx;
	}
	public double[] getMapy() {
		return mapy;
	}
	public void setMapy(double[] mapy) {
		this.mapy = mapy;
	}

}