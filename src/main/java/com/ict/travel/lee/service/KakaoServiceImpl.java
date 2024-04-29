package com.ict.travel.lee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.lee.dao.KakaoDAO;

@Service
public class KakaoServiceImpl implements KakaoService{
	
	@Autowired
	private KakaoDAO kakaoDAO;
}
