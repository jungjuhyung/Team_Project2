package com.ict.travel.cho.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.service.ChoService;

@RestController
public class ChoAjaxController {
	@Autowired
	private ChoService choService;
	
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
	        System.out.println(sb.toString());
	        return sb.toString();
	}
	@RequestMapping(value = "areaSearchTourList", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String areaSearchTourList(@RequestParam("areaCode") String areaCode, 
			@RequestParam("sigunguCode") String sigunguCode, 
			@RequestParam("contentType") String contentType ) throws Exception {
		
			List<ChoTourVO> choTourList = choService.getChoTourList(areaCode,sigunguCode,contentType);
			
			if (choTourList != null) {
				Gson gson = new Gson();
				String jsonString = gson.toJson(choTourList);
				return jsonString;
			}
			return "NO DATA";
	}
}
