package com.ict.travel.jung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.travel.jung.dao.GptDAO;
import com.ict.travel.jung.vo.GptCountVO;

@Service
public class GptServiceImpl implements GptService {
	
	@Autowired
	private GptDAO gptDAO;
	
	@Override
	public List<GptCountVO> getAreaCount(String u_idx) {
		return gptDAO.getAreaCount(u_idx);
	}

	@Override
	public List<GptCountVO> getContentTypeCount(String u_idx) {
		return gptDAO.getContentTypeCount(u_idx);
	}

}
