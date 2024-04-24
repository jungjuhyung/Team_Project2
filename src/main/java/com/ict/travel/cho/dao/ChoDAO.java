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

	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			return sqlSessionTemplate.selectList("cho_mapper.selectTourList", map);
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}

}
