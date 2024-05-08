package com.ict.travel.ko.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoVO;
import com.ict.travel.ko.service.KoService;

@RestController
public class KoAjaxController {

	@RequestMapping(value = "ko_detailCommon1.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String koDetailCommon1(String contentId, String contenttypeId) {
		try {
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailCommon1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="  
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D"); 
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentId);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeId);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
			URL url = new URL(urlBuilder.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = 
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println(result);
				return result;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@RequestMapping(value = "ko_detailIntro1.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String koDetailIntro1(String contentId, String contenttypeId) {
		try {
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailIntro1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="  
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D"); 
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentId);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeId);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			URL url = new URL(urlBuilder.toString());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader br = 
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println(result);
				return result;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@Autowired
	private KoService koService;
	
	@RequestMapping(value = "ko_ajax_area.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koAreaList(String areacode) {
		List<KoVO> area_list = koService.getAreaList(areacode);
			
		Gson gson = new Gson();
		String area_json = gson.toJson(area_list);
		
		return area_json;
	}
	
	@RequestMapping(value = "ko_ajax_tema.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String koTemaList(String contenttypeid) {
		List<KoVO> tema_list = koService.getTemaList(contenttypeid);
		
		Gson gson = new Gson();
		String tema_json = gson.toJson(tema_list);
		
		return tema_json;
	}
	
	
	@RequestMapping(value = "ko_ajax_wish.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String koPlaceWish(String contentid) {
		//System.out.println(contentid);
		
		ItemVO itemVO = koService.getPlaceDetail(contentid);
		if (itemVO != null) {
			String result = koService.getPlaceWish(itemVO);
			System.out.println("getPlaceWish : " + result);
			return result;
		}
		return null;
	}
	
	
	
	
	
}
