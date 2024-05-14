package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.travel.jung.dao.MarkerImgVO;
import com.ict.travel.jung.dao.PathWishVO;
import com.ict.travel.jung.dao.RecommendMarkerOneVO;
import com.ict.travel.jung.dao.RecommendVO;
import com.ict.travel.jung.dao.WishListVO;

public interface MarkerService {
	public List<WishListVO> getWishList(String u_idx);
	public int recommendPostInsert(RecommendVO rcvo);
	public int recommendMarkerInsert(RecommendMarkerOneVO rcmvo);
	public int recommendImgInsert(MarkerImgVO mkivo);
	public List<PathWishVO> getPathWish(String u_idx);
	public List<RecommendVO> getMyRecommend(String u_idx);
}
