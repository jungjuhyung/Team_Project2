package com.ict.travel.cho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChoDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 페이징 카운트
	public int getTourListCount(String areaCode, String sigunguCode, String contentType, String title) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			return sqlSessionTemplate.selectOne("cho_mapper.tourListCount", map);
		} catch (Exception e) {
			System.out.println("검색 카운트" + e);
		}
		return 0;
	}
	// 페이징 검색
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, int offset, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			map.put("offset", offset );
			map.put("limit", limit );
			return sqlSessionTemplate.selectList("cho_mapper.selectTourList", map);
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}


}
