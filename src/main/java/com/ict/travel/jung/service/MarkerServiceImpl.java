package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.jung.dao.BoardVO;
import com.ict.travel.jung.dao.MarkerDAO;
import com.ict.travel.jung.dao.MarkerImgVO;
import com.ict.travel.jung.dao.PathWishVO;
import com.ict.travel.jung.dao.RecommendMarkerOneVO;
import com.ict.travel.jung.dao.RecommendVO;
import com.ict.travel.jung.dao.ReportVO;
import com.ict.travel.jung.dao.WishListVO;

@Service
public class MarkerServiceImpl implements MarkerService {
	@Autowired
	private MarkerDAO markerDAO;
	
	// 내가 찜한 장소 가져오기
	@Override
	public List<WishListVO> getWishList(String u_idx) {
		return markerDAO.getWishList(u_idx) ;
	}

	// 추천경로 작성 정보 삽입
	@Override
	public int recommendPostInsert(RecommendVO rcvo) {
		return markerDAO.recommendPostInsert(rcvo);
	}

	// 추천경로 작성 마커 정보 삽입
	@Override
	public int recommendMarkerInsert(RecommendMarkerOneVO rcmvo) {
		return markerDAO.recommendMarkerInsert(rcmvo);
	}
	
	// 추천경로 작성 마커 이미지 삽입
	@Override
	public int recommendImgInsert(MarkerImgVO mkivo) {
		return markerDAO.recommendImgInsert(mkivo);
	}
	
	// 찜한 추천경로 가져오기
	@Override
	public List<PathWishVO> getPathWish(String u_idx) {
		return markerDAO.getPathWish(u_idx);
	}
	
	// 내가 작성한 추천경로 가져오기
	@Override
	public List<RecommendVO> getMyRecommend(String u_idx) {
		return markerDAO.getMyRecommend(u_idx);
	}
	
	// 내가 작성한 자유게시판 글
	@Override
	public List<BoardVO> getMyBoard(String u_idx) {
		return markerDAO.getMyBoard(u_idx);
	}
	
	// 내가 작성한 신고게시판 글
	@Override
	public List<ReportVO> getMyReport(String u_idx) {
		return markerDAO.getMyReport(u_idx);
	}
}
