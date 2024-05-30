package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ict.travel.jung.vo.BoardVO;
import com.ict.travel.jung.vo.GptCountVO;
import com.ict.travel.jung.vo.MarkerImgVO;
import com.ict.travel.jung.vo.PathWishVO;
import com.ict.travel.jung.vo.RecommendMarkerOneVO;
import com.ict.travel.jung.vo.RecommendVO;
import com.ict.travel.jung.vo.ReportVO;
import com.ict.travel.jung.vo.WishListVO;

public interface RecommendService {
	public List<WishListVO> getWishList(String u_idx);
	public int recommendPostInsert(RecommendVO rcvo);
	public int recommendMarkerInsert(RecommendMarkerOneVO rcmvo);
	public int recommendImgInsert(MarkerImgVO mkivo);
	public List<PathWishVO> getPathWish(String u_idx);
	public List<RecommendVO> getMyRecommend(String u_idx);
	public List<ReportVO> getMyReport(String u_idx);
	public List<BoardVO> getMyBoard(String u_idx);
}
