package com.ict.travel.jung.controller;



import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.travel.cho.dao.PlaceWishVO;
import com.ict.travel.jung.service.RecommendService;
import com.ict.travel.jung.vo.BoardVO;
import com.ict.travel.jung.vo.MarkerImgVO;
import com.ict.travel.jung.vo.PathWishVO;
import com.ict.travel.jung.vo.RecommendMarkerOneVO;
import com.ict.travel.jung.vo.RecommendMarkerVO;
import com.ict.travel.jung.vo.RecommendVO;
import com.ict.travel.jung.vo.ReportVO;
import com.ict.travel.jung.vo.WishListVO;
import com.ict.travel.lee.dao.MemberVO;

@Controller
public class RecommendController {
	
	@Autowired
	private RecommendService marService;
	
	@RequestMapping("recommend_write_go")
	public ModelAndView recommend_write_go(HttpSession session) {
		ModelAndView mv = new ModelAndView("jung_view/recommend_write");
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		List<WishListVO> marker_list = marService.getWishList(uvo.getU_idx());
		mv.addObject("marker_list", marker_list);
		return mv;
	}
	
	@RequestMapping("recommend_write_ok")
	public ModelAndView recommend_write_ok(RecommendVO rcvo, RecommendMarkerVO rcmvo, HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:pathCategory");
		List<MultipartFile[]> marker_img = new ArrayList<MultipartFile[]>();
		Field[] field = rcmvo.getClass().getDeclaredFields();
		String[] mapx = rcmvo.getMapx();
		String[] mapy = rcmvo.getMapy();
		String[] contentid = rcmvo.getContentid();
		String[] areacode = rcmvo.getAreacode();
		String[] sigungucode = rcmvo.getSigungucode();
		String[] contenttypeid = rcmvo.getContenttypeid();
		String[] title = rcmvo.getTitle();
		String[] ex_img = rcmvo.getEx_img();
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		for (int i = 8; i < field.length; i++) {
			try {
				field[i].setAccessible(true);
				System.out.println("ttt"+field[i].get(rcmvo));
				marker_img.add((MultipartFile[])field[i].get(rcmvo));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		try {
			String path = request.getSession().getServletContext().getRealPath("resources/rc_main_img");
			MultipartFile f_main = rcvo.getF_main();
			if (f_main.isEmpty()) {
				rcvo.setFirstimage(ex_img[0]);
				rcvo.setImg_status("1");
			}else {
				// 파일 이름 지정
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString()+"_"+f_main.getOriginalFilename();
				rcvo.setFirstimage(f_name);
				rcvo.setImg_status("0");
				// 파일 업로드(복사)
				byte[] in = f_main.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			rcvo.setU_idx(uvo.getU_idx());
			rcvo.setU_id(uvo.getU_id());
			int res_p = marService.recommendPostInsert(rcvo);
		
		for (int i = 0; i < contenttypeid.length; i++) {
			RecommendMarkerOneVO marker_one = new RecommendMarkerOneVO();
			marker_one.setMapx(mapx[i]);
			marker_one.setMapy(mapy[i]);
			marker_one.setContentid(contentid[i]);
			marker_one.setAreacode(areacode[i]);
			marker_one.setSigungucode(sigungucode[i]);
			marker_one.setContenttypeid(contenttypeid[i]);
			marker_one.setTitle(title[i]);
			int res_m = marService.recommendMarkerInsert(marker_one);
			
			for (MultipartFile k : marker_img.get(i)) {
				try {
					String path2 = request.getSession().getServletContext().getRealPath("/resources/rc_marker_img");
					MarkerImgVO mkivo = new MarkerImgVO();
					if(k.isEmpty()) {
						mkivo.setImage_name(ex_img[i]);
						mkivo.setImg_status("1");
					}else {
						UUID uuid = UUID.randomUUID();
						String f_name = uuid.toString()+"_"+k.getOriginalFilename();
						mkivo.setImage_name(f_name);
						mkivo.setImg_status("0");
						byte[] in = k.getBytes();
						File out = new File(path, f_name);
						FileCopyUtils.copy(in, out);
					}
					int res_i = marService.recommendImgInsert(mkivo);

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return mv;
		}catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("jung_view/error");
	}
	
	@RequestMapping("mypage")
	public ModelAndView mypage(HttpSession session) {
		ModelAndView mv = new ModelAndView("jung_view/mypage");
		MemberVO uvo = (MemberVO) session.getAttribute("memberUser");
		List<WishListVO> wish_place = marService.getWishList(uvo.getU_idx());
		List<PathWishVO> wish_path = marService.getPathWish(uvo.getU_idx());
		List<RecommendVO> my_recommend = marService.getMyRecommend(uvo.getU_idx());
		List<ReportVO> my_report = marService.getMyReport(uvo.getU_idx());
		List<BoardVO> my_board = marService.getMyBoard(uvo.getU_idx());
		mv.addObject("wish_place", wish_place);
		mv.addObject("wish_path", wish_path);
		System.out.println(wish_path);
		mv.addObject("my_recommend", my_recommend);
		mv.addObject("my_report", my_report);
		mv.addObject("my_board", my_board);
		return mv;
	}
}
