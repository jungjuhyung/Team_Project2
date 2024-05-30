package com.ict.travel.cho.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.service.AreaPathService;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class RestAreaPathController {
	
	@Autowired
	private AreaPathService areaPathService;
	
	// 지역 경로 검색
	@RequestMapping(value = "searchAreaPath", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String searchAreaPath(String areaCode, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		
		List<PathPostVO> touristList = areaPathService.getChoTourPathList(areaCode, "999", "12",null,"like","2",0, 10);
		List<PathPostVO> partyList = areaPathService.getChoTourPathList(areaCode, "999", "15",null,"like","2",0, 10);
		List<PathPostVO> restaurantList = areaPathService.getChoTourPathList(areaCode, "999", "39",null,"like","2",0, 10);
		List<PathPostVO> allList = areaPathService.getAllPathPostList();
		
		Map<String, Object> result = new HashMap<>();

	
		// 유저 로그인 상태일 때 찜 여부
		if(uvo != null) {
			List<PathWishVO> pathWishList = areaPathService.getpathWishList(uvo.getU_idx());	
			for (PathPostVO k : touristList) {
				for (PathWishVO j : pathWishList) {
					if(k.getPath_post_idx().equals(j.getPath_post_idx())) {
						k.setU_heart("1");
						break;
					}
				}
			}
			for (PathPostVO k : partyList) {
				for (PathWishVO j : pathWishList) {
					if(k.getPath_post_idx().equals(j.getPath_post_idx())) {
						k.setU_heart("1");
						break;
					}
				}
			}
			for (PathPostVO k : restaurantList) {
				for (PathWishVO j : pathWishList) {
					if(k.getPath_post_idx().equals(j.getPath_post_idx())) {
						k.setU_heart("1");
						break;
					}
				}
			}
			for (PathPostVO k : allList) {
				for (PathWishVO j : pathWishList) {
					if(k.getPath_post_idx().equals(j.getPath_post_idx())) {
						k.setU_heart("1");
						break;
					}
				}
			}
		}
		result.put("touristList", touristList);
		result.put("partyList", partyList);
		result.put("restaurantList", restaurantList);
		result.put("allList", allList);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(result);
		return jsonString;
	}
	
	
	// 경로 찜 추가 
	@RequestMapping(value = "pathWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String pathWishadd(String path_post_idx, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		int result = areaPathService.getPathWishAdd(path_post_idx,uvo.getU_idx());
		return String.valueOf(result);
	}
	
	// 경로 찜 삭제
	@RequestMapping(value = "pathWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String pathWishRemove(String path_post_idx, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		
		int result = areaPathService.getPathWishRemove(path_post_idx,uvo.getU_idx());
		
		return String.valueOf(result);
	}
	

	
}
