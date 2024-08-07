package com.ict.travel.jung.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.jung.vo.BoardVO;
import com.ict.travel.jung.vo.MarkerImgVO;
import com.ict.travel.jung.vo.PathWishVO;
import com.ict.travel.jung.vo.RecommendMarkerOneVO;
import com.ict.travel.jung.vo.RecommendVO;
import com.ict.travel.jung.vo.ReportVO;
import com.ict.travel.jung.vo.WishListVO;

@Repository
public class RecommendDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 장소 위시리스트 가져오기
	public List<WishListVO> getWishList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.wish_list", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 추천경로 작성 정보 삽입
	public int recommendPostInsert(RecommendVO rcvo) {
		try {
			return sqlSessionTemplate.insert("recommend.postInsert", rcvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	// 추천경로 작성 마커 정보 삽입
	public int recommendMarkerInsert(RecommendMarkerOneVO rcmvo) {
		try {
			return sqlSessionTemplate.insert("recommend.markerInsert", rcmvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 추천경로 작성 마커 이미지 삽입
	public int recommendImgInsert(MarkerImgVO mkivo) {
		try {
			return sqlSessionTemplate.insert("recommend.markerImgInsert", mkivo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// 찜한 추천경로 가져오기
	public List<PathWishVO> getPathWish(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.path_wish", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;	
	}
	
	// 내가 작성한 추천경로 가져오기
	public List<RecommendVO> getMyRecommend(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.my_recommend", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 내가 작성한 자유게시판 글
	public List<BoardVO> getMyBoard(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.my_board", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// 내가 작성한 신고게시판 글
	public List<ReportVO> getMyReport(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.my_report", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
