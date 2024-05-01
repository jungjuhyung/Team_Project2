package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.jung.dao.MarkerDAO;
import com.ict.travel.jung.dao.MarkerInfoVO;
import com.ict.travel.jung.dao.WishListVO;

@Service
public class MarkerServiceImpl implements MarkerService {
	@Autowired
	private MarkerDAO markerDAO;
	
	@Override
	public List<WishListVO> getWishList(String u_idx) {
		return markerDAO.getWishList(u_idx) ;
	}

}
