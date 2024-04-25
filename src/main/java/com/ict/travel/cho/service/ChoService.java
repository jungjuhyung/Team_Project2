package com.ict.travel.cho.service;

import java.util.List;

import com.ict.travel.cho.dao.ChoTourVO;

public interface ChoService {

	List<ChoTourVO> getChoTourList(String areaCode, String sigunguCode, String contentType);

}
