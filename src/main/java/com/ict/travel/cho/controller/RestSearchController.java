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
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchOptionVO;
import com.ict.travel.cho.dao.SearchVO;
import com.ict.travel.cho.service.AreaPathService;
import com.ict.travel.cho.service.AreaPlaceService;
import com.ict.travel.cho.service.SearchService;
import com.ict.travel.common.Paging;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class RestSearchController {
	
	@Autowired
	private SearchService searchService;
	@Autowired
	private AreaPathService areaPathService;
	@Autowired
	private AreaPlaceService areaPlaceService;
	@Autowired
	private Paging paging;
	
	// 장소 검색 기능
	@RequestMapping(value = "areaSearchTourList", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String areaSearchTourList(SearchOptionVO optionVO,
			HttpSession session) throws Exception {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		String areaCode = optionVO.getAreaCode();
		String sigunguCode = optionVO.getSigunguCode();
		String contentType = optionVO.getContentType();
		String page = optionVO.getPage();
	 	String title = optionVO.getTitle();
	 	String order = optionVO.getOrder();
	 	String type = optionVO.getType();
	 	int limit = optionVO.getLimit();
		 
		int pagecount = limit;
		
		int count = searchService.getSearchListCount(areaCode,sigunguCode,contentType,title,type);
		paging.setTotalRecord(count);
		// 한 페이지에 20개
		paging.setNumPerPage(pagecount);
		
		// 전체 페이지의 수
		if (paging.getTotalRecord() < paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (((paging.getTotalRecord()*1.0) / paging.getNumPerPage()) % 2 != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		// 현재 페이지 구하기
		String cPage = page;
		if (cPage == null) {
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		
		// 오라클 = begin, end
		// offset구하기 limit * 현재페이지-1
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() -1 ));
		
		paging.setBeginBlock(
				(int)(((paging.getNowPage()-1)/paging.getPagePerBlock()) * paging.getPagePerBlock() + 1)
				);
		
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock()-1);
		
		if(paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
			
		
		List<SearchVO> searchVOList = searchService.getSearchTotal(areaCode,sigunguCode,contentType,title,order,type,paging.getOffset(), paging.getNumPerPage());
		
		if(type.equals("1")) {
			// 유저 로그인 상태일 때 장소 찜 여부
			if(uvo != null) {
				List<PlaceWishVO> placeWishList = areaPlaceService.getPlaceWishList(uvo.getU_idx());	
				for (SearchVO k : searchVOList) {
					for (PlaceWishVO j : placeWishList) {
						if(k.getContentid()!=null &&  k.getContentid().equals(j.getContentid())) {
							System.out.println(1);
							k.setUheart("1");
							break;
						}
					}
				}
			}
		} else {
			// 유저 로그인 상태일 때 게시글 찜 여부
			if(uvo != null) {
				List<PathWishVO> pathWishList = areaPathService.getpathWishList(uvo.getU_idx());	
				for (SearchVO k : searchVOList) {
					for (PathWishVO j : pathWishList) {
						if(k.getPath_post_idx()!=null && k.getPath_post_idx().equals(j.getPath_post_idx())) {
							k.setUheart("1");
							break;
						}
					}
				}
			}
			
		}
	
		Map<String, Object> result = new HashMap<>();

		result.put("searchVOList", searchVOList);
		result.put("paging", paging);
		result.put("type", type);
		Gson gson = new Gson();
		String jsonString = gson.toJson(result);
		return jsonString;
	}
	
	

	
}
