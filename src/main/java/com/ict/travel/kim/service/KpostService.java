package com.ict.travel.kim.service;

import java.util.List;

import com.ict.travel.kim.dao.CommentVO;
import com.ict.travel.kim.dao.KpostVO;

public interface KpostService {
	
	public KpostVO kpostDetail(String path_post_idx);
	
	public List<CommentVO> rcommentList(String path_post_idx);

	public int rcommentInsert(CommentVO commentvo);

	public int rcommentDelete(String comment_idx);
}
