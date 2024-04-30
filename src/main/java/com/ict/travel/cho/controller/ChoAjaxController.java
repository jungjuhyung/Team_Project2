package com.ict.travel.cho.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.common.Paging;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class ChoAjaxController {
	@Autowired
	private ChoService choService;
	
	
	@Autowired
	private Paging paging;
	
	@RequestMapping(value = "sigunguCodeList", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String sigunguCodeList(@RequestParam("areaCode") String areaCode) throws Exception {

			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/areaCode1"); 
	        urlBuilder.append("?" + URLEncoder.encode("MobileOS","UTF-8") + "="+URLEncoder.encode("ETCC","UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "="+URLEncoder.encode("50","UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("areaCode","UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
	        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("S9ue4J7rQOu6yD2thmB8EHNu58T/RX2P7r8WpdefTMO/87Yb9o7XhHJHB+3t6F7/epkxWa4oI8cM4CMB74zpVg==", "UTF-8"));
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        return sb.toString();
	}
	@RequestMapping(value = "areaSearchTourList", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String areaSearchTourList(
			@RequestParam("areaCode") String areaCode, 
			@RequestParam("sigunguCode") String sigunguCode, 
			@RequestParam("contentType") String contentType, 
			@RequestParam("page") String page, 
			@RequestParam("title") String title,
			@RequestParam("limit") int limit,
			HttpSession session) throws Exception {
			
			MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
			// 한 페이지에 일단 20개 - 나중에 입력 받을 수 있음
			int pagecount = limit;
			int count = choService.getTourListCount(areaCode,sigunguCode,contentType,title);
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
			
			List<ChoTourVO> choTourList = choService.getChoTourList(areaCode,sigunguCode,contentType,title,paging.getOffset(), paging.getNumPerPage());
			
			List<PlaceWishVO> placeWishList = choService.getPlaceWishList(uvo.getU_idx());	
			// 유저 로그인 상태일 때 찜 여부
			if(uvo != null) {
				for (ChoTourVO k : choTourList) {
					for (PlaceWishVO j : placeWishList) {
						if(k.getContentid().equals(j.getContentid())) {
							k.setUheart("1");
							break;
						}
					}
				}
			}

			Map<String, Object> result = new HashMap<>();

			result.put("choTourList", choTourList);

			result.put("paging", paging);
				Gson gson = new Gson();
				String jsonString = gson.toJson(result);
				return jsonString;
	}
	
	
	// 찜 추가 
	@RequestMapping(value = "placeWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		int result = choService.getPlaceWishAdd(contentid,uvo.getU_idx());
		return String.valueOf(result);
	}
	
	// 찜 삭제
	@RequestMapping(value = "placeWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
		int result = choService.getPlaceWishRemove(contentid,uvo.getU_idx());
		
		return String.valueOf(result);
	}
	
	
}
