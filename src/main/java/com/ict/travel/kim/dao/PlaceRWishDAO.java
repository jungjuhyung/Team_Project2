package com.ict.travel.kim.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaceRWishDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public PlaceRWishVO placeRDetail(String place_idx) {
		try {
			return sqlSessionTemplate.selectOne("placerwish_t.placeRDetail", place_idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
}
