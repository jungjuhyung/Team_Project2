package com.ict.travel.ko.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.service.PlaceDetailService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class PlaceDetailController {

	@Autowired
	private PlaceDetailService placeDetailService;

	@RequestMapping("ko_detail.do")
	public ModelAndView placeDetail(@ModelAttribute("contentid") String contentid,
			@ModelAttribute("contenttypeid") String contenttypeid, HttpSession session) {
		ModelAndView mv = new ModelAndView("ko_view/place_detail");
		
		//	로그인 여부 체크
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if (uvo != null) {
			mv.addObject("userLogin", "ok");
		}
		
		//	DB 에서 장소 좋아요 수 가져오기
		ItemVO itemVO = placeDetailService.getPlaceDetail(contentid);
		
		// 유저 로그인 상태일 때 찜 여부 체크
		if (uvo != null) {
			List<PlaceWishVO> placeWishList = 
					placeDetailService.getPlaceWishList(uvo.getU_idx());
			for (PlaceWishVO k : placeWishList) {
				if (k.getContentid().equals(contentid)) {
					itemVO.setUheart("1");
					break;
				}
			}
		}
		
		try {
			// 공통 정보 조회
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailCommon1");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentid);
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeid);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=Y");
			urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=Y");
			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 소개 정보 조회
			StringBuilder urlBuilder2 = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/detailIntro1");
			urlBuilder2.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "="
					+ "Rs2hCA9I6Y5q4TlZWwvkT%2BKpf%2FE42e4y5TcRt9HlhfxZzg6r%2FZb7PyaQBN%2Fv183KSU91M9jKg8OvM6pN2TAMAw%3D%3D");
			urlBuilder2.append("&" + URLEncoder.encode("contentId", "UTF-8") + "=" + contentid);
			urlBuilder2.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + contenttypeid);
			urlBuilder2.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=ETC");
			urlBuilder2.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=AppTest");
			urlBuilder2.append("&" + URLEncoder.encode("_type", "UTF-8") + "=json");
			URL url2 = new URL(urlBuilder2.toString());

			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			conn2.setRequestProperty("Content-type", "application/json");

			// 200 이면 성공과 같은 의미 (HttpURLConnection.HTTP_OK)
			int responseCode = conn.getResponseCode();
			int responseCode2 = conn.getResponseCode();
			// System.out.println(responseCode);
			// System.out.println(responseCode2);

			if (responseCode == HttpURLConnection.HTTP_OK && responseCode2 == 200) {
				// 공통 정보 조회
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				// System.out.println(result);

				JSONParser parser = new JSONParser();
				JSONObject obj = (JSONObject) parser.parse(result);
				JSONObject response = (JSONObject) obj.get("response");
				JSONObject body = (JSONObject) response.get("body");
				JSONObject items = (JSONObject) body.get("items");
				JSONArray item = (JSONArray) items.get("item");
				JSONObject item_list = (JSONObject) item.get(0);
				
				itemVO.setContentid(item_list.get("contentid").toString());
				itemVO.setContenttypeid(item_list.get("contenttypeid").toString());
				itemVO.setTitle(item_list.get("title").toString());
				itemVO.setAddr1(item_list.get("addr1").toString());
				itemVO.setMapx(item_list.get("mapx").toString());
				itemVO.setMapy(item_list.get("mapy").toString());
				itemVO.setHomepage(item_list.get("homepage").toString());
				itemVO.setOverview(item_list.get("overview").toString());
				itemVO.setFirstimage(item_list.get("firstimage").toString());

				// 소개 정보 조회
				BufferedReader br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
				String line2 = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line2 = br2.readLine()) != null) {
					sb2.append(line2);
				}
				String result2 = sb2.toString();
				// System.out.println(result2);

				JSONParser parser2 = new JSONParser();
				JSONObject obj2 = (JSONObject) parser2.parse(result2);
				JSONObject response2 = (JSONObject) obj2.get("response");
				JSONObject body2 = (JSONObject) response2.get("body");
				JSONObject items2 = (JSONObject) body2.get("items");
				JSONArray item2 = (JSONArray) items2.get("item");
				JSONObject item_list2 = (JSONObject) item2.get(0);
				
				//	테마별로 가져오는 정보가 다름
				switch (item_list.get("contenttypeid").toString()) {
				//	관광지
				case "12":
					itemVO.setInfocenter(item_list2.get("infocenter").toString());
					itemVO.setParking(item_list2.get("parking").toString());
					itemVO.setRestdate(item_list2.get("restdate").toString());
					itemVO.setUsetime(item_list2.get("usetime").toString());
					break;
				//	행사/공연/축제
				case "15":
					itemVO.setEventplace(item_list2.get("eventplace").toString());
					itemVO.setEventstartdate(item_list2.get("eventstartdate").toString());
					itemVO.setEventenddate(item_list2.get("eventenddate").toString());
					itemVO.setPlaytime(item_list2.get("playtime").toString());
					itemVO.setSponsor1(item_list2.get("sponsor1").toString());
					itemVO.setSponsor1tel(item_list2.get("sponsor1tel").toString());
					itemVO.setUsetimefestival(item_list2.get("usetimefestival").toString());
					break;
				//	음식점
				case "39":
					itemVO.setFirstmenu(item_list2.get("firstmenu").toString());
					itemVO.setTreatmenu(item_list2.get("treatmenu").toString());
					itemVO.setInfocenterfood(item_list2.get("infocenterfood").toString());
					itemVO.setOpentimefood(item_list2.get("opentimefood").toString());
					itemVO.setRestdatefood(item_list2.get("restdatefood").toString());
					break;
				}
				
				//	해당 장소의 상세 정보들
				mv.addObject("itemVO", itemVO);
				
				//	해당 장소가 포함된 추천경로 게시글 가져오기 (좋아요 상위 5개)
				List<KoPostVO> path_list = placeDetailService.getPathList(contentid);
				mv.addObject("path_list", path_list);

				return mv;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
