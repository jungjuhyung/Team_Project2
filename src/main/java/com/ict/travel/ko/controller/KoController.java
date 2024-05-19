package com.ict.travel.ko.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.cho.service.ChoService;
import com.ict.travel.common.Paging;
import com.ict.travel.ko.dao.ItemVO;
import com.ict.travel.ko.dao.KoPostVO;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.ko.service.KoService;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class KoController {

	@Autowired
	private KoService koService;

	@RequestMapping("main_page.do")
	public ModelAndView getKo(HttpSession session) {
		ModelAndView mv = new ModelAndView("ko_view/main_page");

		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if (uvo != null) {
			mv.addObject("userLogin", "ok");
		}

		PopupVO popvo = koService.popupOne();
		mv.addObject("popvo", popvo);

		// 지역별 추천경로 가져오기
		String r_areacode = "1";
		List<KoPostVO> area_list = koService.getAreaList(r_areacode);
		mv.addObject("area_list", area_list);

		// 테마별 추천경로 가져오기
		String r_contenttypeid = "12";
		List<KoPostVO> tema_list = koService.getTemaList(r_contenttypeid);
		mv.addObject("tema_list", tema_list);

		Map<String, String> area = new HashMap<String, String>();
		area.put("1", "서울");
		area.put("2", "인천");
		area.put("3", "대전");
		area.put("4", "대구");
		area.put("5", "광주");
		area.put("6", "부산");
		area.put("7", "울산");
		area.put("8", "세종");
		area.put("31", "경기");
		area.put("32", "강원");
		area.put("33", "충북");
		area.put("34", "충남");
		area.put("35", "경북");
		area.put("36", "경남");
		area.put("37", "전북");
		area.put("38", "전남");
		area.put("39", "제주");
		mv.addObject("area", area);

		Map<String, String> tema = new HashMap<String, String>();
		tema.put("12", "관광지");
		tema.put("15", "행사/공연/축제");
		tema.put("39", "음식점");
		mv.addObject("tema", tema);

		return mv;
	}

	@Autowired
	private ChoService choService;

	@RequestMapping("ko_detail.do")
	public ModelAndView getKoDetail(@ModelAttribute("contentid") String contentid,
			@ModelAttribute("contenttypeid") String contenttypeid, HttpSession session) {
		ModelAndView mv = new ModelAndView("ko_view/detail");
		// System.out.println("contentid : " + contentid);
		// System.out.println("contenttypeid : " + contenttypeid);

		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		if (uvo != null) {
			mv.addObject("userLogin", "ok");
		}

		ItemVO itemVO = koService.getPlaceDetail(contentid);
		// System.out.println("상세페이지 좋아요 수 : " + itemVO.getHeart());

		// 유저 로그인 상태일 때 찜 여부
		if (uvo != null) {
			List<PlaceWishVO> placeWishList = choService.getPlaceWishList(uvo.getU_idx());
			for (PlaceWishVO k : placeWishList) {
				if (k.getContentid().equals(contentid)) {
					itemVO.setUheart("1");
					break;
				}
			}
		}

		List<KoPostVO> path_list = koService.getPathList(contentid);
		// List<KoPathVO> path_list = koService.getPathList(contentid);
		mv.addObject("path_list", path_list);

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

				itemVO.setContentid(item_list.get("contentid").toString());
				itemVO.setContenttypeid(item_list.get("contenttypeid").toString());
				itemVO.setTitle(item_list.get("title").toString());
				itemVO.setAddr1(item_list.get("addr1").toString());
				itemVO.setMapx(item_list.get("mapx").toString());
				itemVO.setMapy(item_list.get("mapy").toString());
				itemVO.setHomepage(item_list.get("homepage").toString());
				itemVO.setOverview(item_list.get("overview").toString());
				itemVO.setFirstimage(item_list.get("firstimage").toString());

				switch (item_list.get("contenttypeid").toString()) {
				case "12":
					itemVO.setExpguide(item_list2.get("expguide").toString());
					itemVO.setInfocenter(item_list2.get("infocenter").toString());
					itemVO.setParking(item_list2.get("parking").toString());
					itemVO.setRestdate(item_list2.get("restdate").toString());
					itemVO.setUsetime(item_list2.get("usetime").toString());
					break;
				case "15":
					itemVO.setEventplace(item_list2.get("eventplace").toString());
					itemVO.setEventstartdate(item_list2.get("eventstartdate").toString());
					itemVO.setEventenddate(item_list2.get("eventenddate").toString());
					itemVO.setPlaytime(item_list2.get("playtime").toString());
					itemVO.setSponsor1(item_list2.get("sponsor1").toString());
					itemVO.setSponsor1tel(item_list2.get("sponsor1tel").toString());
					itemVO.setUsetimefestival(item_list2.get("usetimefestival").toString());
					break;
				case "39":
					itemVO.setFirstmenu(item_list2.get("firstmenu").toString());
					itemVO.setTreatmenu(item_list2.get("treatmenu").toString());
					itemVO.setInfocenterfood(item_list2.get("infocenterfood").toString());
					itemVO.setOpentimefood(item_list2.get("opentimefood").toString());
					itemVO.setRestdatefood(item_list2.get("restdatefood").toString());
					break;
				}
				mv.addObject("itemVO", itemVO);
				return mv;

			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@RequestMapping("footer_terms.do")
	public ModelAndView getTerms() {
		return new ModelAndView("ko_view/footer_terms");
	}

	@RequestMapping("footer_location.do")
	public ModelAndView getLoc() {
		return new ModelAndView("ko_view/footer_location");
	}

	@RequestMapping("footer_email.do")
	public ModelAndView getEmail() {
		return new ModelAndView("ko_view/footer_email");
	}

	// ================================================================ //

	// 팝업관련

	@Autowired
	private Paging paging;

	@RequestMapping("popup_img.do")
	public ModelAndView changePop(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/change_popup");

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = koService.getTotalCount();
		paging.setTotalRecord(count);

		// 전체페이지의 수
		paging.setNumPerPage(3);
		// 한페이지 당 게시물의 수보다 작으면 항상 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 2.
		// 현재페이지 구하자
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 3.
		// offset 구하기
		// limit = numPerPage
		// offset = limit * (현재페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 4.
		// 시작블록과 끝블록 구하기
		// 시작블록 = (int){(현재페이지 -1) / 페이지당 블록수} * 페이지당 블록수 + 1
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5.
		// DB 갔다오기
		List<PopupVO> popup_list = koService.popupList(paging.getOffset(), paging.getNumPerPage());
		if (popup_list != null) {
			mv.addObject("popup_list", popup_list);
			mv.addObject("paging", paging);
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	@PostMapping("popup_img_insert.do")
	public ModelAndView popImage(PopupVO popvo, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:popup_img.do");
			String path = session.getServletContext().getRealPath("/resources/popup_img");

			String u_id = (String) session.getAttribute("u_id");
			popvo.setU_id(u_id);

			MultipartFile file = popvo.getFile();
			if (file.isEmpty()) {
				popvo.setF_name("");
			} else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid + "_" + file.getOriginalFilename();
				popvo.setF_name(f_name);

				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}

			int result = koService.popupInsert(popvo);
			if (result > 0) {
				return mv;
			}
			return new ModelAndView("common_view/error");
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common_view/error");
	}

	@RequestMapping("popup_img_change.do")
	public ModelAndView popImageChange(String popup_idx) {
		ModelAndView mv = new ModelAndView("redirect:main_page.do");

		int result = koService.popupUpdate(popup_idx);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	@RequestMapping("popup_img_delete.do")
	public ModelAndView popImageDelete(String popup_idx) {
		ModelAndView mv = new ModelAndView("redirect:popup_img.do");

		int result = koService.popupDelete(popup_idx);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	// ================================================================ //

	// 유저 관리

	@RequestMapping("user_list.do")
	public ModelAndView getUserList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/user_list");

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = koService.getTotalUser();
		paging.setTotalRecord(count);

		// 전체페이지의 수
		paging.setNumPerPage(10);
		// 한페이지 당 게시물의 수보다 작으면 항상 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 2.
		// 현재페이지 구하자
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 3.
		// offset 구하기
		// limit = numPerPage
		// offset = limit * (현재페이지 - 1)
		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 4.
		// 시작블록과 끝블록 구하기
		// 시작블록 = (int){(현재페이지 -1) / 페이지당 블록수} * 페이지당 블록수 + 1
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5.
		// DB 갔다오기
		List<MemberVO> user_list = koService.getUserList(paging.getOffset(), paging.getNumPerPage());
		if (user_list != null) {
			mv.addObject("user_list", user_list);
			mv.addObject("paging", paging);
			return mv;
		}

		return new ModelAndView("common_view/error");
	}

}
