package com.ict.travel.cho.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminManageDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 관리자 생성
	public String adminCreate(AdminVO adminVO) {
		try {
			int res = sqlSessionTemplate.insert("cho_mapper.AdminCreate", adminVO);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 생성 : " + e);
		}
		return null;
	}
	// 관리자 삭제
	public int adminDelete(String admin_idx) {
		try {
			return sqlSessionTemplate.delete("cho_mapper.AdminDelete", admin_idx);
		} catch (Exception e) {
			System.out.println("관리자 삭제:" + e);
		}
		return 0;
	}
	// 관리자 로그인 시도
	public AdminVO getAdminLogin(AdminVO adminVO) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.selectAdminOne", adminVO);
		} catch (Exception e) {
			System.out.println("관리자 로그인 : " + e);
		}
		return null;
	}
	// 관리자 로그인 확인
	public String getLoginChk(String admin_id) {
		try {
			int res = sqlSessionTemplate.selectOne("cho_mapper.AdminIDChk",admin_id);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 id 중복체크 : " + e);
		}
		return null;
	}
	// 관리자 리스트 페이징용 카운트
	public int getAdminListCount(String text) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.AdminCount", text);
		} catch (Exception e) {
			System.out.println("관리자 cnt :" + e);
		}
		return 0;
	}
	// 관리자 목록
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
	// 관리자 디테일
	public AdminVO getAdminDetail(String admin_idx) {
		try {
			return sqlSessionTemplate.selectOne("cho_mapper.AdminDetail", admin_idx);
		} catch (Exception e) {
			System.out.println("관리자 디테일 : " + e);
		}
		return null;
	}
	// 관리자 수정
	public String getAdminUpdate(AdminVO adminVO) {
		try {
			int res = sqlSessionTemplate.update("cho_mapper.AdminUpdate", adminVO);
			return String.valueOf(res);
		} catch (Exception e) {
			System.out.println("관리자 수정 :" + e);
		}
		return null;
	}
	// API DB 최신화
	public int dataUpdate(List<TourapiVO> voList) {
		try {
			int result = 0;
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
	


}
