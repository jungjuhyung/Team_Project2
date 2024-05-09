package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.travel.jung.dao.MarkerImgVO;
import com.ict.travel.jung.dao.WishListVO;

public interface MarkerService {
	public List<WishListVO> getWishList(String u_idx);
	public int markerImgInsert(MarkerImgVO mkivo);
}
