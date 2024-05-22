package com.ict.travel.cho.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebSocketController {
	@RequestMapping("chatting")
	public ModelAndView chatting() {
		return new ModelAndView("cho_views/chatting");
	}
	@RequestMapping("broadChatting")
	public ModelAndView broadChatting() {
		return new ModelAndView("cho_views/BroadChatting");
	}
}
