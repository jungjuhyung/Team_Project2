package com.ict.travel.jung.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarkerDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<WishListVO> getWishList(String u_idx) {
		try {
			return sqlSessionTemplate.selectList("recommend.wish_list", u_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	public int recommendPostInsert(RecommendVO rcvo) {
		try {
			return sqlSessionTemplate.insert("recommend.postInsert", rcvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int recommendMarkerInsert(RecommendMarkerOneVO rcmvo) {
		try {
			return sqlSessionTemplate.insert("recommend.markerInsert", rcmvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int recommendImgInsert(MarkerImgVO mkivo) {
		try {
			return sqlSessionTemplate.insert("recommend.markerImgInsert", mkivo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
}
