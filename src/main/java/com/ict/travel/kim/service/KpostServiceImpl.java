package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostDAO;
import com.ict.travel.kim.dao.KpostVO;

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

	
	

}
