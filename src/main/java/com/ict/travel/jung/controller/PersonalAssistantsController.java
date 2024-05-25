package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.jung.gpttools.PersonalAssistantsTools;
import com.ict.travel.lee.dao.MemberVO;

@RestController
public class PersonalAssistantsController {
	@Autowired
	private PersonalAssistantsTools perTools;
	
	@RequestMapping(value = "perbot", produces="application/json; charset=utf-8")
	@ResponseBody
	public String personalBot(HttpSession session) {
		MemberVO mvo = (MemberVO) session.getAttribute("memberUser");
		String message = "";
		perTools.perMessageAdd(mvo.getU_thread_id(), message);
		perTools.perAnswerCreate(mvo.getU_thread_id());
		return perTools.perMessagesList(mvo.getU_thread_id());
	}
}