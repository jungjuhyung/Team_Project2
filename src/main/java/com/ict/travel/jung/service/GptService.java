package com.ict.travel.jung.service;

import java.util.List;

import com.ict.travel.jung.vo.GptAwsVO;
import com.ict.travel.jung.vo.GptCountVO;

public interface GptService {
	public List<GptCountVO> getAreaCount(String u_idx);
	public List<GptCountVO> getContentTypeCount(String u_idx);
	public GptAwsVO getAwsValue(String contentid);
}
