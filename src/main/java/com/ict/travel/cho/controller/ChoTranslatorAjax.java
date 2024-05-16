package com.ict.travel.cho.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepl.api.TextResult;
import com.deepl.api.Translator;


@RestController
public class ChoTranslatorAjax {
		
		private Translator translator;
		
		
		@RequestMapping(value = "Translating", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
		@ResponseBody
		public Map<String, String> Translating(
				@RequestParam("sourceText") String sourceText, 
				@RequestParam("targetLang") String targetLang,
				@RequestParam("inputLang") String inputLang
				) throws Exception{
			String sourceLang = null;
			if (inputLang != null && !inputLang.isEmpty()) {
			    sourceLang = inputLang;
			}
			String target = "ko";
			if (targetLang != null && !targetLang.isEmpty()) {
				target = targetLang;
			}
			String authKey = "2811cbde-4818-4a09-814a-a9640875abc2:fx"; // DeepL API 인증 키
		    this.translator = new Translator(authKey);
		    
			Map<String, String> response = new HashMap<>();
			
			try {
				TextResult result = translator.translateText(sourceText,sourceLang, target) ;
				response.put("translatedText", result.getText());
				System.out.println("번역한 글자 : " + result.getText());
			} catch (Exception e) {
				e.printStackTrace();
				response.put("error", "Translation failed");
			}
	        return response;
		}
}
