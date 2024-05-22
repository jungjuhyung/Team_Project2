<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/cho_css/side_bar.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script src="/resources/cho_js/side_bar.js" defer></script>
</head>
<body>
	<div id="side-btn-wrap">
		<button id="translateBtn" class = "translateBtn sbtn side-btn">번역기</button>
		<button id="ChatBtn" class ="ChatBot sbtn side-btn">챗봇</button>
	</div>
	
		<!-- 챗봇 -->
		<div class = "modalwrap">
			<div class="modal_box5">
				<div>GPT에게 질문하기</div> <div class="close-button-container"><input type="button" class="closeBot" value = "X"></div>
				<div id="chat-container">
			        <div id="chat-messages"></div>
			        <div id="user-input">
			            <input type="text" placeholder="메시지를 입력하세요..." />
			            <button class ="sbtn">전송</button>
			        </div>
			    </div>
			</div>
		</div>
		
		<!-- 번역기 -->
		<div class = "modalwrap">
			<div class="modal_box4">
					<div id = "lang-Wrapper">
						<!-- 입력할 언어 선택 -->
						<div class="dropdown">
						    <div id="dropdown-btn" onclick="toggleDropdown()">언어 감지</div>
						    <div class="dropdown-content" id="langDropdown">
						        <div class="dropdown-item" onclick="selectLang('null')">언어감지</div>
								<div class="dropdown-item" onclick="selectLang('AR')">아랍어</div>
								<div class="dropdown-item" onclick="selectLang('BG')">불가리아어</div>
								<div class="dropdown-item" onclick="selectLang('CS')">체코어</div>
								<div class="dropdown-item" onclick="selectLang('DA')">덴마크어</div>
								<div class="dropdown-item" onclick="selectLang('DE')">독일어</div>
								<div class="dropdown-item" onclick="selectLang('EL')">그리스어</div>
								<div class="dropdown-item" onclick="selectLang('EN')">영어</div>
								<div class="dropdown-item" onclick="selectLang('ES')">스페인어</div>
								<div class="dropdown-item" onclick="selectLang('ET')">에스토니아어</div>
								<div class="dropdown-item" onclick="selectLang('FI')">핀란드어</div>
								<div class="dropdown-item" onclick="selectLang('FR')">프랑스어</div>
								<div class="dropdown-item" onclick="selectLang('HU')">헝가리어</div>
								<div class="dropdown-item" onclick="selectLang('ID')">인도네시아어</div>
								<div class="dropdown-item" onclick="selectLang('IT')">이탈리아어</div>
								<div class="dropdown-item" onclick="selectLang('JA')">일본어</div>
								<div class="dropdown-item" onclick="selectLang('KO')">한국어</div>
								<div class="dropdown-item" onclick="selectLang('LT')">리투아니아어</div>
								<div class="dropdown-item" onclick="selectLang('LV')">라트비아어</div>
								<div class="dropdown-item" onclick="selectLang('NB')">노르웨이 보크몰</div>
								<div class="dropdown-item" onclick="selectLang('NL')">네덜란드어</div>
								<div class="dropdown-item" onclick="selectLang('PL')">폴란드어</div>
								<div class="dropdown-item" onclick="selectLang('PT')">포르투갈어</div>
								<div class="dropdown-item" onclick="selectLang('RO')">루마니아어</div>
								<div class="dropdown-item" onclick="selectLang('RU')">러시아어</div>
								<div class="dropdown-item" onclick="selectLang('SK')">슬로바키아어</div>
								<div class="dropdown-item" onclick="selectLang('SL')">슬로베니아어</div>
								<div class="dropdown-item" onclick="selectLang('SV')">스웨덴어</div>
								<div class="dropdown-item" onclick="selectLang('TR')">터키어</div>
								<div class="dropdown-item" onclick="selectLang('UK')">우크라이나어</div>
								<div class="dropdown-item" onclick="selectLang('ZH')">중국어 (간체)</div>
						        <!-- 다른 언어들을 추가합니다 -->
						    </div>
						</div>
						<!-- 번역되서 나올 언어 선택 -->
						<div class="dropdown">
						     <div id="dropdown-btn2" onclick="toggleDropdown2()">한국어</div>
						    <div class="dropdown-content" id="langDropdown2">
								<div class="dropdown-item" onclick="selectLang2('AR')">아랍어</div>
								<div class="dropdown-item" onclick="selectLang2('BG')">불가리아어</div>
								<div class="dropdown-item" onclick="selectLang2('CS')">체코어</div>
								<div class="dropdown-item" onclick="selectLang2('DA')">덴마크어</div>
								<div class="dropdown-item" onclick="selectLang2('DE')">독일어</div>
								<div class="dropdown-item" onclick="selectLang2('EL')">그리스어</div>
								<div class="dropdown-item" onclick="selectLang2('EN-US')">영어(영국식)</div>
								<div class="dropdown-item" onclick="selectLang2('EN-GB')">영어(미국식)</div>
								<div class="dropdown-item" onclick="selectLang2('ES')">스페인어</div>
								<div class="dropdown-item" onclick="selectLang2('ET')">에스토니아어</div>
								<div class="dropdown-item" onclick="selectLang2('FI')">핀란드어</div>
								<div class="dropdown-item" onclick="selectLang2('FR')">프랑스어</div>
								<div class="dropdown-item" onclick="selectLang2('HU')">헝가리어</div>
								<div class="dropdown-item" onclick="selectLang2('ID')">인도네시아어</div>
								<div class="dropdown-item" onclick="selectLang2('IT')">이탈리아어</div>
								<div class="dropdown-item" onclick="selectLang2('JA')">일본어</div>
								<div class="dropdown-item" onclick="selectLang2('KO')">한국어</div>
								<div class="dropdown-item" onclick="selectLang2('LT')">리투아니아어</div>
								<div class="dropdown-item" onclick="selectLang2('LV')">라트비아어</div>
								<div class="dropdown-item" onclick="selectLang2('NB')">노르웨이 보크몰</div>
								<div class="dropdown-item" onclick="selectLang2('NL')">네덜란드어</div>
								<div class="dropdown-item" onclick="selectLang2('PL')">폴란드어</div>
								<div class="dropdown-item" onclick="selectLang2('PT-BR')">포르투갈어(브라질)</div>
								<div class="dropdown-item" onclick="selectLang2('PT-PT')">포르투갈어</div>
								<div class="dropdown-item" onclick="selectLang2('RO')">루마니아어</div>
								<div class="dropdown-item" onclick="selectLang2('RU')">러시아어</div>
								<div class="dropdown-item" onclick="selectLang2('SK')">슬로바키아어</div>
								<div class="dropdown-item" onclick="selectLang2('SL')">슬로베니아어</div>
								<div class="dropdown-item" onclick="selectLang2('SV')">스웨덴어</div>
								<div class="dropdown-item" onclick="selectLang2('TR')">터키어</div>
								<div class="dropdown-item" onclick="selectLang2('UK')">우크라이나어</div>
								<div class="dropdown-item" onclick="selectLang2('ZH')">중국어 (간체)</div>
						        <!-- 다른 언어들을 추가합니다 -->
						    </div>
						</div>
					</div>
					<br>
					
					
					<div id = "textArea">
						<textarea id = "sourceText" class = "textBox"  placeholder="텍스트 입력"></textarea>
						<textarea id = "transText" class="textBox" placeholder="번역" disabled="disabled"></textarea>
					</div>
					
					<input type="hidden" id = "targetLang">
					<input type="hidden" id = "inputLang">
					<input type="button" class="closeTran sbtn" value = "닫기">
					<input type="button" class="sbtn" onclick="translating()" value = "번역">
			</div>
		</div>
</body>
</html>