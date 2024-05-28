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
	public int getTourListCount(String areaCode, String sigunguCode, String contentType, String title, String type) {
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
	// 페이징 검색
	public List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType, String title, String order, String type, int offset, int limit) {
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

	public AdminVO getAdminLogin(AdminVO adminVO) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.selectAdminOne", adminVO);
		} catch (Exception e) {
			System.out.println("관리자 로그인 : " + e);
		}
		return null;
	}

	public List<AdminVO> getAdminList(String text, int offset, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("text", text);
			map.put("offset", offset);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("cho_mapper.AdminList", map);
		} catch (Exception e) {
			System.out.println("관리자 목록 확인 : " + e);
		}
		return null;
	}

	public int adminDelete(String admin_idx) {
		try {
			return sqlSessionTemplate.delete("cho_mapper.AdminDelete", admin_idx);
		} catch (Exception e) {
			System.out.println("관리자 삭제:" + e);
		}
		return 0;
	}

	public String getLoginChk(String admin_id) {
		try {
			int res = sqlSessionTemplate.selectOne("cho_mapper.AdminIDChk",admin_id);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 id 중복체크 : " + e);
		}
		return null;
	}

	public String adminCreate(AdminVO adminVO) {
		try {
			int res = sqlSessionTemplate.insert("cho_mapper.AdminCreate", adminVO);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 생성 : " + e);
		}
		return null;
	}

	public AdminVO getAdminDetail(String admin_idx) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.AdminDetail", admin_idx);
		} catch (Exception e) {
			System.out.println("관리자 디테일 : " + e);
		}
		return null;
	}

	public String getAdminUpdate(AdminVO adminVO) {
		try {
			int res = sqlSessionTemplate.update("cho_mapper.AdminUpdate", adminVO);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 수정 :" + e);
		}
		return null;
	}

	public int getAdminListCount(String text) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.AdminCount", text);
		} catch (Exception e) {
			System.out.println("관리자 cnt :" + e);
		}
		return 0;
	}

	public List<ChoTourVO> getRandomTourList(String areaCode, int limit) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaCode", areaCode);
			map.put("limit", limit);
			return sqlSessionTemplate.selectList("cho_mapper.RandomTourList", map);
		} catch (Exception e) {
			System.out.println("랜덤 TourList :" + e);
		}
		return null;
	}
	public List<PathPostVO> getAllPathPostList() {
		try {
			return sqlSessionTemplate.selectList("cho_mapper.RandomPathList");
		} catch (Exception e) {
			System.out.println("PathPostList :" + e);
		}
		return null;
	}


}
