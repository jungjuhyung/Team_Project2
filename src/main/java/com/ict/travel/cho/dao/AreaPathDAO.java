package com.ict.travel.cho.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.travel.lee.dao.MemberVO;

@Repository
public class AreaPathDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	// 경로 호출
	public List<PathPostVO> getChoTourPathList(String areaCode, String sigunguCode, String contentType, String title,
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
			map.put("type", type );
			List<PathPostVO> list = sqlSessionTemplate.selectList("cho_mapper.selectTourPathList", map);
			for (PathPostVO k : list) {
				System.out.println("제목: " + k.getTitle());
			}
			return sqlSessionTemplate.selectList("cho_mapper.selectTourPathList", map);
		} catch (Exception e) {
			System.out.println("지역 검색" + e);
		}
		return null;
	}

	public List<PathWishVO> getpathWishList(String u_idx) {
		return sqlSessionTemplate.selectList("cho_mapper.selectpathWishList", u_idx);
	}
	// 경로 위시리스트 추가
	public int getPathWishAdd(String path_post_idx, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			PathWishVO pathVO = sqlSessionTemplate.selectOne("cho_mapper.selectPathWishOne", path_post_idx);
			map.put("u_idx", u_idx);
			map.put("pathWishVO", pathVO);
			result += sqlSessionTemplate.insert("cho_mapper.pathWishAdd", map);
			
			if(result > 0) {
				result += sqlSessionTemplate.update("cho_mapper.pathPostAddHeart",path_post_idx);	
			}
			
			transactionManager.commit(status);
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println(e);
		}
		return 0;
	}
	
	// 경로 위시리스트 삭제 
	public int getPathWishRemove(String path_post_idx, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			PathWishVO placeVO = sqlSessionTemplate.selectOne("cho_mapper.selectPathWishOne", path_post_idx);
			map.put("u_idx", u_idx);
			map.put("pathWishVO", placeVO);
			result += sqlSessionTemplate.delete("cho_mapper.pathWishRemove", map);
			
			if(result > 0) {
				result += sqlSessionTemplate.update("cho_mapper.pathRemoveHeart", path_post_idx);	
			}
			transactionManager.commit(status);
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println(e);
		}
		return 0;
	}
	
	// 토탈 경로 리스트
	public List<PathPostVO> getAllPathPostList() {
		try {
			return sqlSessionTemplate.selectList("cho_mapper.RandomPathList");
		} catch (Exception e) {
			System.out.println("PathPostList :" + e);
		}
		return null;
	}


}
