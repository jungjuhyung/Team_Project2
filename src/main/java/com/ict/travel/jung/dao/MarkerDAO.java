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
			return sqlSessionTemplate.selectList("marker.wish_list", u_idx);
		} catch (Exception e) {
			System.out.println("오류니?");
			System.out.println(e);
		}
		return null;
	}
}
