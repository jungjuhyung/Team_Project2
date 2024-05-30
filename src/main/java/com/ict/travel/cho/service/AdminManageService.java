package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.dao.TourapiVO;
public interface AdminManageService {
	
	// 관리자 생성
	String adminCreate(AdminVO adminVO);
	// 관리자 삭제
	int adminDelete(String admin_idx);
	// 관리자 로그인 시도
	AdminVO getAdminLogin(AdminVO adminVO);
	// 관리자 로그인 확인
	String getLoginChk(String admin_id);
	// 관리자 목록 페이징 카운트
	int getAdminListCount(String text);
	// 관리자 목록
	List<AdminVO> getAdminList(String text, int offset, int limit);
	// 관리자 디테일
	AdminVO getAdminDetail(String admin_idx);
	// 관리자 수정
	String getAdminUpdate(AdminVO adminVO);
	// API DB 최신화
	int dataUpdate(List<TourapiVO> voList);
}
