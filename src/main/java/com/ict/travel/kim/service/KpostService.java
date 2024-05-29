package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;
import com.ict.travel.lee.dao.MemberVO;

public interface KpostService {
	
	public KpostVO kpostDetail(String path_post_idx);
	
	public List<CommentVO> rcommentList(String path_post_idx);

	public int rcommentInsert(CommentVO commentvo);

	public int rcommentDelete(String comment_idx);
	
	public int ilikethis(MemberVO membervo, KpostVO kpostvo);

	public int ilikehit(KpostVO kpostvo);

	public int ihatethis(MemberVO membervo, KpostVO kpostvo);
	
	public int ihatehit(KpostVO kpostvo);
	
	public KpostVO pathTable(String path_post_idx);
	
	public int pathDelete(String path_post_idx);
}
