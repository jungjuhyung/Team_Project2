package com.ict.travel.jung.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarkerDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MarkerInfoVO getMarkerInfo(String contentid) {
		try {
			MarkerInfoVO test = sqlSessionTemplate.selectOne("marker.wish_list", contentid);
			System.out.println(test);
			System.out.println(test.getPlace_title());
			return test;
		} catch (Exception e) {
			System.out.println("오류니?");
			System.out.println(e);
		}
		return null;
	}
}
