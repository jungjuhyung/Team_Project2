package com.ict.travel.jung.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ict.travel.jung.gpttools.ChatAssistantsTools;

@RestController
public class ChatAssiGPTController {
	@Autowired
	private ChatAssistantsTools chatTools;
	// ChatBot 생성
	@RequestMapping(value = "chatbot", produces="application/json; charset=utf-8")
	@ResponseBody
	public synchronized String chatBot() {
		//String test = "그럼 대천해수욕장의 현재 날씨를 알려줘";
		//chatTools.chatMessageAdd(test);
		//chatTools.chatAnswerCreate();
		return chatTools.chatMessagesList();
	}
	
}