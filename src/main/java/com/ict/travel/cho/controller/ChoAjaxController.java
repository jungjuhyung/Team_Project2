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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.common.Paging;

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
	public String areaSearchTourList(@RequestParam("areaCode") String areaCode, 
			@RequestParam("sigunguCode") String sigunguCode, 
			@RequestParam("contentType") String contentType, @RequestParam("page") String page  ) throws Exception {
			// 한 페이지에 일단 20개 - 나중에 입력 받을 수 있음
			int pagecount = 20;
			int count = choService.getTourListCount(areaCode,sigunguCode,contentType);
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
			System.out.println("전체 갯수 " + count);
			
			List<ChoTourVO> choTourList = choService.getChoTourList(areaCode,sigunguCode,contentType,paging.getOffset(), paging.getNumPerPage());
			// Create a Map to hold both tour list and pagination information
			Map<String, Object> result = new HashMap<>();

			// Add tour list to the result map
			result.put("choTourList", choTourList);

			// Add pagination information to the result map
			result.put("paging", paging);
				Gson gson = new Gson();
				String jsonString = gson.toJson(result);
				return jsonString;
	}
}
