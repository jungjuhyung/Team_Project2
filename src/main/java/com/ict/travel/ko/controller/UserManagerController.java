package com.ict.travel.ko.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.ko.dao.PageVO;
import com.ict.travel.ko.dao.UserStopVO;
import com.ict.travel.ko.dao.UserVO;
import com.ict.travel.ko.service.UserManagerService;

@Controller
public class UserManagerController {

	@Autowired
	private UserManagerService userManagerService;

	// 유저 관리
	@Autowired
	private Paging paging;

	// 유저 목록 리스트
	@RequestMapping("user_list.do")
	public ModelAndView getUserList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/user_list");

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = userManagerService.getTotalUser();
		paging.setTotalRecord(count);
		// 한페이지 당 게시물의 수
		paging.setNumPerPage(5);
		// 전체페이지의 수
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
		paging.setBeginBlock((int) ((paging.getNowPage() - 1) 
				/ paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		// 끝블록 = 시작블록 + 페이지당 블록수 - 1
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);
		// 끝블록이 전체페이지 수보다 크면 끝블록에 전체페이지 수를 넣어주자
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		// 5. 정지만료날짜가 현재날짜와 같거나 크면 정상으로 바꿔주자
		List<UserVO> stop_user = userManagerService.getStopUser();
		if (stop_user != null) {
			for (UserVO k : stop_user) {
				String stopdate = k.getU_stopdate();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
				LocalDateTime stop = LocalDateTime.parse(stopdate, formatter);
				LocalDateTime now = LocalDateTime.now();
				if (stop.isBefore(now)) {
					int result = userManagerService.getStopState(k.getU_idx(), k.getUstop_idx());
				}
			}
		}

		// 6.
		// DB 갔다오기
		List<UserVO> user_list = userManagerService.getUserList(paging.getOffset(), paging.getNumPerPage());
		for (UserVO k : user_list) {
			if (k.getU_state().equals("1")) {
				UserStopVO ustop = userManagerService.getStopDetail(k.getU_idx());
				k.setUstop_idx(ustop.getUstop_idx());
				k.setAdmin_id(ustop.getAdmin_id());
				k.setStop_note(ustop.getStop_note());
			}
		}
		mv.addObject("user_list", user_list);
		mv.addObject("paging", paging);
		return mv;
	}

	// 유저 정지하기
	@RequestMapping("stop_update.do")
	public ModelAndView stopUpdate(String stop_days, String u_idx, String stop_note, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect: user_list.do");

		String admin_id = (String) session.getAttribute("admin_id");
		int result = userManagerService.getStopUpdate(stop_days, u_idx, stop_note, admin_id);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	// 정지 해제하기
	@RequestMapping("stop_reset.do")
	public ModelAndView stopReset(String u_idx, String ustop_idx) {
		ModelAndView mv = new ModelAndView("redirect: user_list.do");
		int result = userManagerService.getStopState(u_idx, ustop_idx);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	// 유저 검색
	@RequestMapping("user_search.do")
	public ModelAndView searchUser(String search, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/user_list");

		int count = userManagerService.getSearchTotal(search);
		paging.setTotalRecord(count);

		paging.setNumPerPage(5);
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);

		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		PageVO pvo = new PageVO(search, paging.getOffset(), paging.getNumPerPage());
		List<UserVO> user_list = userManagerService.getSearchUser(pvo);
		for (UserVO k : user_list) {
			if (k.getU_state().equals("1")) {
				UserStopVO ustop = userManagerService.getStopDetail(k.getU_idx());
				k.setUstop_idx(ustop.getUstop_idx());
				k.setAdmin_id(ustop.getAdmin_id());
				k.setStop_note(ustop.getStop_note());
			}
		}
		mv.addObject("user_list", user_list);
		mv.addObject("paging", paging);
		return mv;
	}
}
