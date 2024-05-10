package com.ict.travel.kim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
