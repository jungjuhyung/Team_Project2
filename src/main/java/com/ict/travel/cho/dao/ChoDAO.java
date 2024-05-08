package com.ict.travel.cho.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.travel.lee.dao.MemberVO;

@Repository
public class ChoDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public MemberVO getUserLogin(String string) {
		return sqlSessionTemplate.selectOne("cho_mapper.sessionInsert", string);
	}
	
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
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, int offset, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			map.put("offset", offset );
			map.put("limit", limit );
			map.put("order", order );
			return sqlSessionTemplate.selectList("cho_mapper.selectTourList", map);
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}
	// 위시리스트 호출
	public List<PlaceWishVO> getPlaceWishList(String u_idx) {
		return sqlSessionTemplate.selectList("cho_mapper.selectPlaceWishList",u_idx);
	}

	// 위시리스트 추가
	public int getPlaceWishAdd(String contentid, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			PlaceWishVO placeVO = sqlSessionTemplate.selectOne("cho_mapper.selectPlaceOne", contentid);
			map.put("u_idx", u_idx);
			map.put("placeWishVO", placeVO);
			result += sqlSessionTemplate.insert("cho_mapper.placeWishAdd", map);
			if(result > 0) {
			result += sqlSessionTemplate.update("cho_mapper.placeAddHeart",contentid);	
			}
			transactionManager.commit(status);
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println(e);
		}
		return 0;
	}
	// 위시리스트 삭제
	public int getPlaceWishRemove(String contentid, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			PlaceWishVO placeVO = sqlSessionTemplate.selectOne("cho_mapper.selectPlaceOne", contentid);
			map.put("u_idx", u_idx);
			map.put("placeWishVO", placeVO);
			result += sqlSessionTemplate.delete("cho_mapper.placeWishRemove", map);
			
			if(result > 0) {
				result += sqlSessionTemplate.update("cho_mapper.placeRemoveHeart", contentid);	
			}
			transactionManager.commit(status);
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println(e);
		}
		return 0;
	}
	
	public int dataUpdate(List<TourapiVO> voList) {
		try {
			int result = 0;
			int cnt = 0;
			int success = 0;
			List<TourapiVO> tempList = new ArrayList<TourapiVO>();
			// 데이터 정제
			for (TourapiVO k : voList) {
				if(k.getFirstimage() != null && !k.getFirstimage().equals("") && 
						Double.parseDouble(k.getMapy()) > 30 && Double.parseDouble(k.getMapy()) < 45 && 
						Double.parseDouble(k.getMapx()) > 120 && Double.parseDouble(k.getMapx()) < 135) {
					tempList.add(k);
				}else {
				}
			}
			// 데이터 수정, 삽입
			for (TourapiVO k : tempList) {
				result= sqlSessionTemplate.update("cho_mapper.placeUpdate", k);
				if(result != 1) {
					sqlSessionTemplate.insert("cho_mapper.placeInsert", k);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	public List<ChoTourVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title,
			String order, int offset, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("sigunguCode", sigunguCode);
			map.put("contentType", contentType);
			map.put("title", title);
			map.put("offset", offset );
			map.put("limit", limit );
			map.put("order", order );
			return sqlSessionTemplate.selectList("cho_mapper.selectTourPathList", map);
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}


}
