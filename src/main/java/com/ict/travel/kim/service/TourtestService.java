package com.ict.travel.kim.service;


import java.util.List;

import com.ict.travel.kim.dao.TourtestVO;

public interface TourtestService {

	public List<TourtestVO> tourDetail(String path_post_idx);
	
	public List<TourtestVO> tourMaps(String path_post_idx);
}
