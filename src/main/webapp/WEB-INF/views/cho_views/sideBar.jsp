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
		<button id="BroadChatBtn" class ="BroadChat sbtn side-btn">익명채팅방</button>
		<button id="askChatBtn" class ="askChatBtn sbtn side-btn">관리자문의</button>
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

		<!-- 챗봇 -->
		<div class = "modalwrap">
			<div class="modal_box5">
				<div>GPT에게 질문하기</div> <div class="close-button-container"><input type="button" class="closeBot" value = "X"></div>
				<div id="chat-container">
			        <div id="chat-messages"></div>
			        <div id="gpt-user-input">
			            <input type="text" placeholder="메시지를 입력하세요..." />
			            <button class ="sbtn">전송</button>
			        </div>
			    </div>
			</div>
		</div>
		
		<!-- 익명 채팅방 -->
		<div class = "modalwrap">
			<div class="modal_box6">
			  <!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
			  	<textarea id="messageTextArea1" rows="10" cols="50"></textarea>
				<form id = "broadChat">
				    <!-- 유저 명을 입력하는 텍스트 박스(기본 값은 anonymous(익명)) -->
				    <div class = "broad-div"><span>닉네임 :</span> <input id="user1" type="text" value="anonymous"></div>
				    <br>
				    <!-- 송신 메시지를 작성하는 텍스트 박스 -->
				    <div class = "broad-div"><span>채팅 :</span>  <input id="textMessage1" type="text"></div>
				    <!-- 메세지를 송신하는 버튼 -->
				    <div>
					    <input type="button" class="closeBroad sbtn" value = "닫기">
						<input type="button" class="sbtn" onclick="sendMessage1()" value = "전송">
					</div>
				</form>
			  
			  <br />
			</div>
		</div>
		
		<!-- 관리자 문의 -->
		<div class = "modalwrap">
			<div class="modal_box7">
			  <!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
			  	<textarea id="messageTextArea2" rows="10" cols="50"></textarea>
				<form id = "askChat">
				    <!-- 유저 명을 입력하는 텍스트 박스(기본 값은 anonymous(익명)) -->
				    <div class = "broad-div"><span>아이디 :</span> <input id="user2" type="text" value="${u_id }" disabled></div>
				    <br>
				    <!-- 송신 메시지를 작성하는 텍스트 박스 -->
				    <div class = "broad-div"><span>채팅 :</span>  <input id="textMessage2" type="text"></div>
				    <!-- 메세지를 송신하는 버튼 -->
				    <div>
					    <input type="button" class="closeAsk sbtn" value = "닫기">
						<input type="button" class="sbtn" onclick="sendMessage2()" value = "전송">
					</div>
				</form>
			  <br />
			</div>
		</div>
			
			
		
</body>
</html>
