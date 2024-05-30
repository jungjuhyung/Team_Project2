package com.ict.travel.cho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.cho.dao.AdminManageDAO;
import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.dao.TourapiVO;

@Service
public class AdminManageServiceImpl implements AdminManageService{
	@Autowired
	private AdminManageDAO adminManageDAO;
	
	// 관리자 생성
	@Override
	public String adminCreate(AdminVO adminVO) {
		return adminManageDAO.adminCreate(adminVO);
	}
	// 관리자 삭제
	@Override
	public int adminDelete(String admin_idx) {
		return adminManageDAO.adminDelete(admin_idx);
	}
	// 관리자 로그인 시도
	@Override
	public AdminVO getAdminLogin(AdminVO adminVO) {
		return adminManageDAO.getAdminLogin(adminVO);
	}
	// 관리자 로그인 확인
	@Override
	public String getLoginChk(String admin_id) {
		return adminManageDAO.getLoginChk(admin_id);
	}
	// 관리자 목록 페이징용 카운트
	@Override
	public int getAdminListCount(String text) {
		return adminManageDAO.getAdminListCount(text);
	}
	// 관리자 목록
	@Override
	public List<AdminVO> getAdminList(String text, int offset, int limit) {
		return adminManageDAO.getAdminList(text, offset, limit);
	}
	// 관리자 디테일
	@Override
	public AdminVO getAdminDetail(String admin_idx) {
		return adminManageDAO.getAdminDetail(admin_idx);
	}
	// 관리자 수정
	@Override
	public String getAdminUpdate(AdminVO adminVO) {
		return adminManageDAO.getAdminUpdate(adminVO);
	}
	
	// API DB 최신화
	@Override
	public int dataUpdate(List<TourapiVO> voList) {
		return adminManageDAO.dataUpdate(voList);
	}
	
}

