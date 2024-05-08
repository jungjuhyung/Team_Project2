package com.ict.travel.kim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.TourtestDAO;
import com.ict.travel.kim.dao.TourtestVO;

@Service
public class TourtestServiceImpl implements TourtestService {

	@Autowired
	private TourtestDAO tourtestDAO;

	@Override
	public TourtestVO tourDetail(String path_maker_idx) {
		return tourtestDAO.tourDetail(path_maker_idx);
	}

	

	
}
