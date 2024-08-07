package com.ict.travel.lee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ict.travel.cho.dao.AdminVO;
import com.ict.travel.cho.service.AdminManageService;
import com.ict.travel.common.PersonalAssistantsTools;
import com.ict.travel.jung.service.GptService;
import com.ict.travel.jung.vo.GptCountVO;
import com.ict.travel.lee.dao.MemberVO;
import com.ict.travel.lee.service.MailService;
import com.ict.travel.lee.service.MemberService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.google.gson.reflect.TypeToken;
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private AdminManageService adminService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	// 개인 gpt 연동을 위해 주형이 추가한 것
	@Autowired
	private PersonalAssistantsTools perTools;
	@Autowired
	private GptService gptService;
	
	@RequestMapping("agree_go.do")
	public ModelAndView getAgreeMent() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/agreement");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	@RequestMapping("agree_acc.do")
	public ModelAndView getSignUp_2() {
		try {
			ModelAndView mv = new ModelAndView("lee_view/joinForm");
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	// 아이디 중복 체크(회원가입)
	@RequestMapping(value = "getIdChk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getIdChk(String u_id) {
		String result = memberService.getIdChk(u_id);
		return result;
	}
	
	// 닉네임 중복 체크(회원정보 수정)
	@RequestMapping(value = "getNickChk.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getNickChk(String u_nickname) {
		String res = memberService.getNickChk(u_nickname);
		return res;
	}
	// 비밀번호 체크(회원가입)
	@RequestMapping(value = "chkPassword.do", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String chkPassword(String u_pwd, String u_idx) {
		System.out.println("비번체크 ajax");
		System.out.println(u_idx);
		System.out.println(u_pwd);
		MemberVO mvo = memberService.getMemberUpDetail(u_idx);
		if (! passwordEncoder.matches(u_pwd, mvo.getU_pwd())) {
			return "0";
		}else {
			return "1";
		}
		//String res = memberService.chkPassword(u_pwd);
	}
	
	@RequestMapping("join_success_go.do")
	public ModelAndView getSignUp(MemberVO mvo, HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("lee_view/loginForm");
		    
			// GPT 개인 스레드 부여
			Gson gson = new Gson();
	        Type type = new TypeToken<Map<String, Object>>() {}.getType();
	        String perThread = perTools.perThreadCreate();
	        Map<String, Object> per_id = gson.fromJson(perThread, type);
	        
			String u_id = request.getParameter("u_id");
			String u_pwd = request.getParameter("u_pwd");
			String u_name = request.getParameter("u_name");
			String u_birth = request.getParameter("u_birth");
			
			
			String u_email = request.getParameter("u_email");
			String u_gender = request.getParameter("u_gender");
			String u_nickname = request.getParameter("u_nickname");
			String u_self = request.getParameter("u_self");
			String n_status = request.getParameter("n_status");
			String k_status = request.getParameter("k_status");
			String u_pwd2 = passwordEncoder.encode(u_pwd);
	        
			MemberVO mvo1 = new MemberVO();
			mvo1.setU_id(u_id);
			mvo1.setU_pwd(u_pwd2);
			mvo1.setU_name(u_name);
			mvo1.setU_birth(u_birth);
			mvo1.setU_email(u_email);
			mvo1.setU_gender(u_gender);
			mvo1.setU_nickname(u_nickname);
			mvo1.setU_self(u_self);
			mvo1.setN_status(n_status);
			mvo1.setK_status(k_status);
			mvo1.setU_per_thread_id(per_id.get("id").toString());
			int result = memberService.getSignUp(mvo1);
			System.out.println(result);
			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("error");
	}
	
	
	@RequestMapping("login_go_ok.do")
	public ModelAndView getLoginOK(HttpServletRequest request, MemberVO mvo) {
		try {
			HttpSession session = request.getSession();
			ModelAndView mv = new ModelAndView("redirect:main_page.do");
			AdminVO adminVO = new AdminVO();
			adminVO.setAdmin_id(mvo.getU_id());
			adminVO.setAdmin_pwd(mvo.getU_pwd());
			AdminVO adminVO2 = adminService.getAdminLogin(adminVO);
			if(adminVO2 != null) {
			
				if(! passwordEncoder.matches(adminVO.getAdmin_pwd(), adminVO2.getAdmin_pwd())) {
					mv.setViewName("redirect:login_go.do");
					return mv;
				}else  {
					if(adminVO2.getAdmin_state().equals("0")) {
						mv.setViewName("redirect:login_go.do");
						return mv;
					}else {
						session.setAttribute("adminUser", adminVO2);
						session.setAttribute("admin_id", adminVO2.getAdmin_id());
						session.setAttribute("admin_idx", adminVO2.getAdmin_idx());
						session.setAttribute("admin_grade", adminVO2.getAdmin_grade());
						return mv;
					}
				}
			}
			
			MemberVO mvo2 = memberService.getLoginOK(mvo);
			
			if( mvo2== null || ! passwordEncoder.matches(mvo.getU_pwd(), mvo2.getU_pwd())) {
				String errorMessage = "비밀번호가 틀렸습니다. 다시 시도해주세요.";
	            mv.addObject("errorMessage", errorMessage);
				mv.setViewName("lee_view/loginForm");
				return mv;
			}
			else {
				session.setAttribute("memberUser", mvo2);
				session.setAttribute("u_id", mvo2.getU_id());
				session.setAttribute("u_idx", mvo2.getU_idx());
				
				// 유저의 wish별 gpt의 추천 내용을 세션에 넣기
				List<GptCountVO> areaCount = gptService.getAreaCount(mvo2.getU_idx());
				List<GptCountVO> contentTypeCount = gptService.getContentTypeCount(mvo2.getU_idx());
				System.out.println("area"+areaCount);
				System.out.println("content"+contentTypeCount);
				String message = "";
				StringBuffer sb = new StringBuffer();
				if (areaCount.isEmpty() && contentTypeCount.isEmpty()) {
					message = "유저의 위시리스트가 비어있습니다. vector_storage(vs_PhohXJdcZlxuz5yNEzJIsK9m)에 저장되어있는 데이터에서 여행갈만한 장소를 5곳 추천해줘.";
				}else {
					sb.append("[");
					for (GptCountVO k : areaCount) {
						String content = "arearcode:"+k.getAreacode()+"을 찜한 개수="+k.getAreacode_count()+"개 & ";
						sb.append(content);
					} 
					for (GptCountVO k : contentTypeCount) {
						String content = "contenttypeid:"+k.getContenttypeid()+"을 찜한 개수="+k.getContenttypeid_count()+"개 & ";
						sb.append(content);
					}
					sb.append("]");
					sb.append("해당 정보를 바탕으로 vector_storage(vs_PhohXJdcZlxuz5yNEzJIsK9m)에 저장되어있는 데이터에서 여행갈만한 장소를 5곳 추천해줘.");
					message = sb.toString();
				}
				System.out.println(message);
				perTools.perMessageAdd(mvo2.getU_per_thread_id(), message);
				perTools.perAnswerCreate(mvo2.getU_per_thread_id());
				String gptAws = perTools.perMessagesList(mvo2.getU_per_thread_id());
				
				Gson gson = new Gson();
				JsonParser parser = new JsonParser();
		        try {
		            // JSON 문자열을 JsonElement로 파싱
		            JsonElement jsonElement = parser.parse(gptAws);

		            // JsonElement를 JsonObject로 변환
		            JsonObject jsonObject = jsonElement.getAsJsonObject();

		            // data 필드에서 content 필드의 value 값을 추출
		            List<String> values = new ArrayList<>();
		            JsonArray dataArray = jsonObject.getAsJsonArray("data");
		            for (JsonElement element : dataArray) {
		                JsonObject dataObject = element.getAsJsonObject();
		                JsonArray contentArray = dataObject.getAsJsonArray("content");
		                for (JsonElement contentElement : contentArray) {
		                    JsonObject contentObject = contentElement.getAsJsonObject();
		                    JsonObject textObject = contentObject.getAsJsonObject("text");
		                    String value = textObject.get("value").getAsString();
		                    values.add(value);
		                }
		            }
		            System.out.println(values.get(0).replace(" ", ""));
		            session.setAttribute("gptAws", values.get(0).replace(" ", ""));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

				return new ModelAndView("redirect:main_page.do"); 

			}
		} catch (Exception e) {
			return new ModelAndView("error");
		}
	}
	
	
}





