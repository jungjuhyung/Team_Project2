package com.ict.travel.cho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.travel.lee.dao.MemberVO;

@Repository
public class SearchDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public MemberVO getUserLogin(String string) {
		return sqlSessionTemplate.selectOne("cho_mapper.sessionInsert", string);
	}
	
	// 페이징 카운트
	public int getSearchListCount(String areaCode, String sigunguCode, String contentType, String title, String type) {
		try {
			int res = 0;
			int res2 = 0;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			map.put("type", type);
			if (type.equals("1")) {
				res = sqlSessionTemplate.selectOne("cho_mapper.tourListCount", map);
			} else if(type.equals("2")) {
				List<String> post_idxList = sqlSessionTemplate.selectList("cho_mapper.selectPostIdxList",map);
				if(post_idxList.size() > 0) {
					map.put("post_idxList", post_idxList);
				}
				res2 = sqlSessionTemplate.selectOne("cho_mapper.tourPathListCount", map);
			} else {
				res = sqlSessionTemplate.selectOne("cho_mapper.tourListCount", map);
				res2 = sqlSessionTemplate.selectOne("cho_mapper.tourPathListCount", map);
			}
			return res + res2;
		} catch (Exception e) {
			System.out.println("검색 카운트" + e);
		}
		return 0;
	}
	// 조건 검색
	public List<SearchVO> getSearchTotal(String areaCode, String sigunguCode, String contentType, String title,
			String order, String type, int offset, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			map.put("offset", offset );
			map.put("limit", limit );
			map.put("order", order );
		
			if(type.equals("1")){
				return sqlSessionTemplate.selectList("cho_mapper.selectSearchTourList", map);
			} else if(type.equals("2")){
				List<String> post_idxList = sqlSessionTemplate.selectList("cho_mapper.selectPostIdxList",map);
				if(post_idxList.size() > 0) {
					map.put("post_idxList", post_idxList);
					System.out.println(title);
				}
				return sqlSessionTemplate.selectList("cho_mapper.selectSearchPathList", map);
			}
			
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}
	


}
