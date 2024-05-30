package com.ict.travel.ko.dao;

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

import com.ict.travel.cho.dao.PlaceWishVO;

@Repository
public class PlaceDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	//	해당 장소에 대한 정보들을 DB 에서 가져오기 
	public ItemVO getPlaceDetail(String contentid) {
		return sqlSessionTemplate.selectOne("ko_detail.place_detail", contentid);
	}

	// 위시리스트 호출
	public List<PlaceWishVO> getPlaceWishList(String u_idx) {
		return sqlSessionTemplate.selectList("ko_detail.place_wish", u_idx);
	}

	// 위시리스트 추가
	public int getPlaceWishAdd(String contentid, String u_idx) {
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			//	위시리스트에 들어갈때 해당 장소에 대한 정보를 넣어주기 위해 DB 에서 장소에 대한 정보를 가져오기
			PlaceWishVO placeVO = sqlSessionTemplate.selectOne("ko_detail.selectPlaceOne", contentid);
			map.put("u_idx", u_idx);
			map.put("placeWishVO", placeVO);
			result += sqlSessionTemplate.insert("ko_detail.placeWishAdd", map);
			if (result > 0) {
				result += sqlSessionTemplate.update("ko_detail.placeAddHeart", contentid);
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
			PlaceWishVO placeVO = sqlSessionTemplate.selectOne("ko_detail.selectPlaceOne", contentid);
			map.put("u_idx", u_idx);
			map.put("placeWishVO", placeVO);
			result += sqlSessionTemplate.delete("ko_detail.placeWishRemove", map);

			if (result > 0) {
				result += sqlSessionTemplate.update("ko_detail.placeRemoveHeart", contentid);
			}
			transactionManager.commit(status);
			return result;
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println(e);
		}
		return 0;
	}
	
	//	해당 장소가 포함된 추천경로 리스트 가져오기
	public List<KoPostVO> getPathList(String contentid) {
		return sqlSessionTemplate.selectList("ko_detail.path_list", contentid);
	}
}
