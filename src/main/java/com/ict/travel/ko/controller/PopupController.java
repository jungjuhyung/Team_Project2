package com.ict.travel.ko.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.common.Paging;
import com.ict.travel.ko.dao.PopupVO;
import com.ict.travel.ko.service.PopupService;

@Controller
public class PopupController {

	@Autowired
	private PopupService popupService;

	@Autowired
	private Paging paging;

	// 팝업 이미지 변경 페이지
	@RequestMapping("popup_img.do")
	public ModelAndView changePop(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("ko_view/popup_list");

		// 페이징 기법
		// 1.
		// 전체 게시물의 수를 구하자
		int count = popupService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 한페이지 당 게시물 설정 : 3개
		paging.setNumPerPage(3);
		
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
		List<PopupVO> popup_list = popupService.popupList(paging.getOffset(), paging.getNumPerPage());
		if (popup_list != null) {
			mv.addObject("popup_list", popup_list);
			mv.addObject("paging", paging);
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	// 팝업 이미지 추가
	@PostMapping("popup_img_insert.do")
	public ModelAndView popImage(PopupVO popvo, HttpSession session) {
		try {
			ModelAndView mv = new ModelAndView("redirect:popup_img.do");
			String path = session.getServletContext().getRealPath("/resources/popup_img");
			String admin_id = (String) session.getAttribute("admin_id");
			popvo.setAdmin_id(admin_id);

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
			int result = popupService.popupInsert(popvo);
			if (result > 0) {
				return mv;
			}
			return new ModelAndView("common_view/error");
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("common_view/error");
	}
	

	// 팝업창 바꾸기
	@RequestMapping("popup_img_change.do")
	public ModelAndView popImageChange(String popup_idx) {
		ModelAndView mv = new ModelAndView("redirect:main_page.do");

		int result = popupService.popupUpdate(popup_idx);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

	// 팝업 이미지 삭제
	@RequestMapping("popup_img_delete.do")
	public ModelAndView popImageDelete(String popup_idx) {
		ModelAndView mv = new ModelAndView("redirect:popup_img.do");

		int result = popupService.popupDelete(popup_idx);
		if (result > 0) {
			return mv;
		}
		return new ModelAndView("common_view/error");
	}

}
