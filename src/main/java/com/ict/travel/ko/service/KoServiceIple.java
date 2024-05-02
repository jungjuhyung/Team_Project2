package com.ict.travel.ko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.ko.dao.KoDAO;
import com.ict.travel.ko.dao.KoVO;

@Service
public class KoServiceIple implements KoService{
	
	@Autowired
	private KoDAO koDAO;

	@Override
	public List<KoVO> getAreaList(String areacode) {
		return koDAO.getAreaList(areacode);
	}

	@Override
	public List<KoVO> getTemaList(String contenttypeid) {
		return koDAO.getTemaList(contenttypeid);
	}
	

}
