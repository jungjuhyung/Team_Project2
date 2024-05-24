<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
</head>
<body>
  <!-- 채팅 영역 -->
  <form>
    <!-- 텍스트 박스에 채팅의 내용을 작성한다. -->
    <input id="textMessage" type="text" >
    
    <!-- 서버로 메시지를 전송하는 버튼 -->
    <input onclick="sendMessage()" value="Send" type="button">
  </form>

  <br />
  <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
  <textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
  
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script type="text/javascript">
  	// 타이틀 검색 엔터 처리
	document.querySelector('#textMessage').addEventListener('keypress', function(e) {
	    if (e.key === 'Enter') {
	        e.preventDefault(); // 기본 제출 동작 방지
	        sendMessage();
	    }
	});
  	
  	
    var webSocket = new WebSocket("ws://192.168.0.70:8090/qnasocket");
    
    // 콘솔 텍스트 영역
    var messageTextArea = document.getElementById("messageTextArea");
    
    // 접속이 완료되면
    webSocket.onopen = function(message) {
      // 콘솔에 메시지를 남긴다.
      messageTextArea.value += "Server connect...\n";
    };
    
    // 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
    webSocket.onclose = function(message) { };
    
    // 에러가 발생하면
    webSocket.onerror = function(message) {
      // 콘솔에 메시지를 남긴다.
      messageTextArea.value += "error...\n";
    };
    
    // 서버로부터 메시지가 도착하면 콘솔 화면에 메시지를 남긴다.
    webSocket.onmessage = function(message) {
      messageTextArea.value += "(operator) => " + message.data + "\n";
    };
    
    // 서버로 메시지를 발송하는 함수
    // Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
    function sendMessage() {
      // 텍스트 박스의 객체를 가져옴
      let message = document.getElementById("textMessage");
      
      // 콘솔에 메세지를 남긴다.
      messageTextArea.value += "(me) => " + message.value + "\n";
      
      // 소켓으로 보낸다.
      webSocket.send(message.value);
      
      // 텍스트 박스 추기화
      message.value = "";
    }
    
  </script>
</body>
</html>