package com.ict.travel.kim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.kim.dao.PlaceRWishDAO;
import com.ict.travel.kim.dao.PlaceRWishVO;

@Service
public class PlaceRWishServiceImpl implements PlaceRWishService {

	@Autowired
	private PlaceRWishDAO placeRWishDAO;

	@Override
	public PlaceRWishVO placeRDetail(String place_idx) {
		return placeRWishDAO.placeRDetail(place_idx);
	}
	
	

}
