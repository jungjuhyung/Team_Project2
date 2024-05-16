package com.ict.travel.kim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.TourtestDAO;
import com.ict.travel.kim.dao.TourtestVO;

@Service
public class TourtestServiceImpl implements TourtestService {

	@Autowired
	private TourtestDAO tourtestDAO;

	@Override
	public List<TourtestVO> tourDetail(String path_post_idx) {
		return tourtestDAO.tourDetail(path_post_idx);
	}

	@Override
	public List<TourtestVO> tourMaps(String path_post_idx) {
		return tourtestDAO.tourMaps(path_post_idx);
	}

	

	
}
