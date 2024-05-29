package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostDAO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.lee.dao.MemberVO;

@Service
public class KpostServiceImpl implements KpostService{
	
	@Autowired
	private KpostDAO kpostDAO;
	
	@Override
	public KpostVO kpostDetail(String path_post_idx) {
		return kpostDAO.kpostDetail(path_post_idx);
	}

	@Override
	public List<CommentVO> rcommentList(String path_post_idx) {
		return kpostDAO.rcommentList(path_post_idx);
	}

	@Override
	public int rcommentInsert(CommentVO commentvo) {
		return kpostDAO.rcommentInsert(commentvo);
	}

	@Override
	public int rcommentDelete(String comment_idx) {
		return kpostDAO.rcommentDelete(comment_idx);
	}

	@Override
	public int ilikethis(MemberVO membervo, KpostVO kpostvo) {
		return kpostDAO.ilikethis(membervo, kpostvo);
	}
	
	@Override
	public int ilikehit(KpostVO kpostvo) {
		return kpostDAO.ilikehit(kpostvo);
	}

	@Override
	public int ihatethis(MemberVO membervo, KpostVO kpostvo) {
		return kpostDAO.ihatethis(membervo, kpostvo);
	}
	
	@Override
	public int ihatehit(KpostVO kpostvo) {
		return kpostDAO.ihatehit(kpostvo);
	}

	@Override
	public KpostVO pathTable(String path_post_idx) {
		return kpostDAO.pathTable(path_post_idx);
	}

	@Override
	public int pathDelete(String path_post_idx) {
		return kpostDAO.pathDelete(path_post_idx);
	}

	


	
	

}
