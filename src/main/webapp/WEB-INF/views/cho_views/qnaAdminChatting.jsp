<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>

</head>
<body>
  <!-- 유저가 접속할 때마다 이 템플릿으로 채팅창을 생성한다. -->
  <div class="template" style="display:none">
  
    <form>
      <!-- 메시지 텍스트 박스 -->
      <input type="text" class="message">
      <!-- 전송 버튼 -->
      <input value="Send" type="button" class="sendBtn">
    </form>
    
    <br />
    
    <!-- 서버와 메시지를 주고 받는 콘솔 텍스트 영역 -->
    <textarea rows="10" cols="50" class="console" disabled="disabled" style="resize: none;"></textarea>
  </div>
  
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  
  <script type="text/javascript">
    // 서버의 admin의 서블릿으로 웹 소켓을 한다.
    var webSocket = new WebSocket("ws://192.168.0.70:8090/adminqnasocket");
    
    // 운영자에서의 open, close, error는 의미가 없어서 형태만 선언
    webSocket.onopen = function(message) { };
    webSocket.onclose = function(message) { };
    webSocket.onerror = function(message) { };

 // 서버로부터 메시지가 올 때 처리
    webSocket.onmessage = function(message) {
      console.value += message.data + "\n";
      let node = JSON.parse(message.data);
      console.log(message);

      // 메시지의 상태에 따라 처리
      if(node.status === "visit") {
        // 방문 메시지 처리
        let form = $(".template").html();
        form = $("<div class ='newChat'>"+node.key+"</div>").attr("data-key", node.key).append(form);
        $("body").append(form);
      } else if(node.status === "message") {
        // 메시지 메시지 처리
        let nickname = node.message.match(/{{(.*?)}}/)[1]; // 닉네임 추출
        let actualMessage = node.message.replace(/{{(.*?)}}/,""); // 실제 메시지 추출
        let $div = $("[data-key='"+node.key+"']");
        let log = $div.find(".console").val();
        $div.find(".console").val(log + "(" + nickname + ") => " + actualMessage + "\n");
      } else if(node.status === "bye") {
        // 종료 메시지 처리
        $("[data-key='"+node.key+"']").remove();
      }
    };
    
    // 전송 버튼을 클릭하면 발생하는 이벤트
    $(document).on("click", ".sendBtn", function(){
      // div 태그를 찾는다.
      let $div = $(this).closest(".newChat");
      
      // 메시지 텍스트 박스를 찾아서 값을 취득한다.
      let message = $div.find(".message").val();
      
      // 유저 key를 취득한다.
      let key = $div.data("key");
      
      // console영역을 찾는다.
      let log = $div.find(".console").val();
      
      // 아래에 메시지를 추가한다.
      $div.find(".console").val(log + "(me) => " + message + "\n");
      
      // 텍스트 박스의 값을 초기화 한다.
      $div.find(".message").val("");
      
      // 웹소켓으로 메시지를 보낸다.
      webSocket.send(key+"#####"+message);
    });
    
    // 텍스트 박스에서 엔터키를 누르면
    $(document).on("keydown", ".message", function(){
      // keyCode 13은 엔터이다.
      if(event.keyCode === 13) {
        // 버튼을 클릭하는 트리거를 발생한다.
        $(this).closest(".float-left").find(".sendBtn").trigger("click");
        // form에 의해 자동 submit을 막는다.
        return false;
      }
      return true;
    });
  </script>
</body>
</html>
