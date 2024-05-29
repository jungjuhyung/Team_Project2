package com.ict.travel.cho.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.SearchVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.common.Paging;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class RestAreaPlaceController {
	
	@Autowired
	private ChoService choService;
	
	// 시군구 코드 가져오기
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

	// 테마,지역별 장소 호출
	@RequestMapping(value = "searchAreaPlace", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String searchAreaPlace(String areaCode, HttpSession session, String type) {
		try {
			
		
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		
		List<ChoTourVO> touristList = choService.getChoTourList(areaCode, "999", "12",null,"like","1",0, 10);
		List<ChoTourVO> partyList = choService.getChoTourList(areaCode, "999", "15",null,"like","1",0, 10);
		List<ChoTourVO> restaurantList = choService.getChoTourList(areaCode, "999", "39",null,"like","1",0, 10);
		List<ChoTourVO> randomList = choService.getRandomTourList(areaCode,3);
	
		Map<String, Object> result = new HashMap<>();

	
		// 유저 로그인 상태일 때 찜 여부
		if(uvo != null) {
			List<PlaceWishVO> placeWishList = choService.getPlaceWishList(uvo.getU_idx());	
			for (ChoTourVO k : touristList) {
				for (PlaceWishVO j : placeWishList) {
					if(k.getContentid().equals(j.getContentid())) {
						k.setUheart("1");
						break;
					}
				}
			}
			for (ChoTourVO k : partyList) {
				for (PlaceWishVO j : placeWishList) {
					if(k.getContentid().equals(j.getContentid())) {
						k.setUheart("1");
						break;
					}
				}
			}
			for (ChoTourVO k : restaurantList) {
				for (PlaceWishVO j : placeWishList) {
					if(k.getContentid().equals(j.getContentid())) {
						k.setUheart("1");
						break;
					}
				}
			}
			for (ChoTourVO k : randomList) {
				for (PlaceWishVO j : placeWishList) {
					if(k.getContentid().equals(j.getContentid())) {
						k.setUheart("1");
						break;
					}
				}
			}
		}
		result.put("touristList", touristList);
		result.put("partyList", partyList);
		result.put("restaurantList", restaurantList);
		result.put("randomList", randomList);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(result);
		return jsonString;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null ;
	}
	
	
	// 장소 찜 추가 
	@RequestMapping(value = "placeWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		int result = choService.getPlaceWishAdd(contentid,uvo.getU_idx());
		return String.valueOf(result);
	}
	
	// 장소 찜 삭제
	@RequestMapping(value = "placeWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		
		int result = choService.getPlaceWishRemove(contentid,uvo.getU_idx());
		
		return String.valueOf(result);
	}

	
}
