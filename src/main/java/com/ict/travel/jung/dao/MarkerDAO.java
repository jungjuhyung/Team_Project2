package com.ict.travel.jung.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarkerDAO {
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
}
