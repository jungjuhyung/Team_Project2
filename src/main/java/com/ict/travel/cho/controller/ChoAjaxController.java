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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ict.travel.cho.dao.ChoTourVO;
import com.ict.travel.cho.dao.DataFetcher;
import com.ict.travel.cho.dao.PathPostVO;
import com.ict.travel.cho.dao.PathWishVO;
import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.dao.TourapiParser;
import com.ict.travel.cho.dao.TourapiVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.common.Paging;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class ChoAjaxController {
	
	@Autowired
	private ChoService choService;
	@Autowired
	private TourapiParser tourapiParser;
	@Autowired
	private DataFetcher dataFetcher;
	
	@Autowired
	private Paging paging;
	
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

	// 장소 검색 기능
	@RequestMapping(value = "areaSearchTourList", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String areaSearchTourList(
			@RequestParam("areaCode") String areaCode, 
			@RequestParam("sigunguCode") String sigunguCode, 
			@RequestParam("contentType") String contentType, 
			@RequestParam("page") String page, 
			@RequestParam("title") String title,
			@RequestParam("limit") int limit,
			@RequestParam("order") String order,
			@RequestParam("type") String type,
			HttpSession session) throws Exception {
			
			MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
			// 한 페이지에 일단 20개 - 나중에 입력 받을 수 있음
			int pagecount = limit;
			int count = choService.getTourListCount(areaCode,sigunguCode,contentType,title,type);
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
			
			List<ChoTourVO> choTourList = choService.getChoTourList(areaCode,sigunguCode,contentType,title,order,type,paging.getOffset(), paging.getNumPerPage());
			
			// 유저 로그인 상태일 때 찜 여부
			if(uvo != null) {
				List<PlaceWishVO> placeWishList = choService.getPlaceWishList(uvo.getU_idx());	
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
	
	
	// 장소 찜 추가 
	@RequestMapping(value = "placeWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishInsert(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		int result = choService.getPlaceWishAdd(contentid,uvo.getU_idx());
		return String.valueOf(result);
	}
	
	// 장소 찜 삭제
	@RequestMapping(value = "placeWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getWishDelete(String contentid, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
		int result = choService.getPlaceWishRemove(contentid,uvo.getU_idx());
		
		return String.valueOf(result);
	}
	
	// 테마,지역별 장소 호출
	@RequestMapping(value = "searchAreaPlace", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String searchAreaPlace(String areaCode, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
		List<ChoTourVO> touristList = choService.getChoTourList(areaCode, "999", "12",null,"like",0, 4);
		List<ChoTourVO> partyList = choService.getChoTourList(areaCode, "999", "15",null,"like",0, 4);
		List<ChoTourVO> restaurantList = choService.getChoTourList(areaCode, "999", "39",null,"like",0, 4);
		
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
		}
		result.put("touristList", touristList);
		result.put("partyList", partyList);
		result.put("restaurantList", restaurantList);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(result);
		return jsonString;
	}
	
	// 지역 경로 검색
	@RequestMapping(value = "searchAreaPath", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String searchAreaPath(String areaCode, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
		List<PathPostVO> touristList = choService.getChoTourPathList(areaCode, "999", "12",null,"like",0, 4);
		List<PathPostVO> partyList = choService.getChoTourPathList(areaCode, "999", "15",null,"like",0, 4);
		List<PathPostVO> restaurantList = choService.getChoTourPathList(areaCode, "999", "39",null,"like",0, 4);
		
		Map<String, Object> result = new HashMap<>();

	
		// 유저 로그인 상태일 때 찜 여부
		if(uvo != null) {
			List<PathWishVO> pathWishList = choService.getpathWishList(uvo.getU_idx());	
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
		}
		result.put("touristList", touristList);
		result.put("partyList", partyList);
		result.put("restaurantList", restaurantList);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(result);
		return jsonString;
	}
	
	
	// 경로 찜 추가 
	@RequestMapping(value = "pathWishAdd", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String pathWishadd(String path_post_idx, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		int result = choService.getPathWishAdd(path_post_idx,uvo.getU_idx());
		return String.valueOf(result);
	}
	
	// 경로 찜 삭제
	@RequestMapping(value = "pathWishRemove", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String pathWishRemove(String path_post_idx, HttpSession session) {
		MemberVO uvo = (MemberVO) session.getAttribute("userVO");
		
		int result = choService.getPathWishRemove(path_post_idx,uvo.getU_idx());
		
		return String.valueOf(result);
	}
	
	
	
	// -----------------------
	
	// 디비 최신화
	@RequestMapping(value = "updateTest", produces = "application/json; charset=utf-8" )
	@ResponseBody
	public String updateDB() throws Exception{
	
		
        String result1 = dataFetcher.fetchData("12");
        String result2 = dataFetcher.fetchData("15");
        String result3 = dataFetcher.fetchData("39");
        
        // JSON 데이터를 JSONObject로 파싱
        JSONObject obj1 = (JSONObject) JSONValue.parse(result1);
        JSONObject response1 = (JSONObject) obj1.get("response");
        JSONObject body1 = (JSONObject) response1.get("body");
        JSONObject items1 = (JSONObject) body1.get("items");
        JSONArray itemArray1 = (JSONArray) items1.get("item");
        
        JSONObject obj2 = (JSONObject) JSONValue.parse(result2);
        JSONObject response2 = (JSONObject) obj2.get("response");
        JSONObject body2 = (JSONObject) response2.get("body");
        JSONObject items2 = (JSONObject) body2.get("items");
        JSONArray itemArray2 = (JSONArray) items2.get("item");
        
        JSONObject obj3 = (JSONObject) JSONValue.parse(result3);
        JSONObject response3 = (JSONObject) obj3.get("response");
        JSONObject body3 = (JSONObject) response3.get("body");
        JSONObject items3 = (JSONObject) body3.get("items");
        JSONArray itemArray3 = (JSONArray) items3.get("item");
        
    
        // itemArray를 TourapiParser를 사용하여 List<TourapiVO>로 파싱
        List<TourapiVO> touristVoList = tourapiParser.parseJsonToVO(itemArray2.toString());
        List<TourapiVO> PartyVoList = tourapiParser.parseJsonToVO(itemArray1.toString());
        List<TourapiVO> restaurantVoList = tourapiParser.parseJsonToVO(itemArray3.toString());

        choService.dataUpdate(touristVoList);
		choService.dataUpdate(PartyVoList);
		choService.dataUpdate(restaurantVoList);
        
        return "Success";


	}
	
		

	
}
