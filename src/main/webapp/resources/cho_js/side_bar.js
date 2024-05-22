//번역기
let cnt = 0;
let cnt2 = 0;
$(document).on("click", "#translateBtn", function () {
  if (cnt % 2 == 0) {
    $(".modal_box4").fadeIn(100);
  } else {
    $(".modal_box4").fadeOut(1);
  }
  $(".modal_box5").fadeOut(1);
  cnt2 = 0;
  cnt++;
});

$(document).on("click", ".closeTran", function () {
  $(".modal_box4").fadeOut(1);
  cnt++;
});

$(document).on("click", "#ChatBtn", function () {
  if (cnt2 % 2 == 0) {
    $(".modal_box5").fadeIn(100);
  } else {
    $(".modal_box5").fadeOut(1);
  }
  $(".modal_box4").fadeOut(1);
  cnt = 0;
  cnt2++;
});

$(document).on("click", ".closeBot", function () {
  $(".modal_box5").fadeOut(1);
  cnt2++;
});

$(document).on("keyup", "#sourceText", function () {
  adjustTextareaHeight();
});

function translating() {
  let sourceText = $("#sourceText").val();
  let targetLang = $("#targetLang").val();
  let inputLang = $("#inputLang").val();
  $.ajax({
    url: "Translating",
    type: "post",
    data: {
      sourceText: sourceText,
      targetLang: targetLang,
      inputLang: inputLang,
    },
    dataType: "json",
    success: function (response) {
      handleTranslationResponse(response);
      console.log(response);
    },
    error: function () {
      alert("실패");
    },
  });
}

function handleTranslationResponse(response) {
  let transText = document.getElementById("transText");
  // AJAX로 가져온 번역된 내용을 transText에 삽입
  transText.value = response.translatedText;
  // 번역된 내용이 변경되었으므로 높이를 조절
  adjustTextareaHeight();
}

function adjustTextareaHeight() {
  let sourceText = document.getElementById("sourceText");
  let transText = document.getElementById("transText");

  sourceText.style.height = "auto"; // 높이를 자동으로 설정
  transText.style.height = "auto"; // 높이를 자동으로 설정
  let maxHeight = Math.max(sourceText.scrollHeight, transText.scrollHeight);
  // textarea의 높이를 내용에 맞게 조절
  console.log(maxHeight);
  sourceText.style.height = maxHeight + "px";
  transText.style.height = maxHeight + "px";
}

function toggleDropdown() {
  document.getElementById("langDropdown").classList.toggle("show");
  document.getElementById("langDropdown2").classList.remove("show");
}

function selectLang(langCode) {
  document.getElementById("langDropdown").classList.remove("show");
  document.getElementById("dropdown-btn").innerText = getLanguageName(langCode);
  document.getElementById("inputLang").value = langCode;
}

function getLanguageName(langCode) {
  switch (langCode) {
    case "AR":
      return "아랍어";
    case "BG":
      return "불가리아어";
    case "CS":
      return "체코어";
    case "DA":
      return "덴마크어";
    case "DE":
      return "독일어";
    case "EL":
      return "그리스어";
    case "EN":
      return "영어";
    case "ES":
      return "스페인어";
    case "ET":
      return "에스토니아어";
    case "FI":
      return "핀란드어";
    case "FR":
      return "프랑스어";
    case "HU":
      return "헝가리어";
    case "ID":
      return "인도네시아어";
    case "IT":
      return "이탈리아어";
    case "JA":
      return "일본어";
    case "KO":
      return "한국어";
    case "LT":
      return "리투아니아어";
    case "LV":
      return "라트비아어";
    case "NB":
      return "노르웨이 보크몰";
    case "NL":
      return "네덜란드어";
    case "PL":
      return "폴란드어";
    case "PT":
      return "포르투갈어";
    case "RO":
      return "루마니아어";
    case "RU":
      return "러시아어";
    case "SK":
      return "슬로바키아어";
    case "SL":
      return "슬로베니아어";
    case "SV":
      return "스웨덴어";
    case "TR":
      return "터키어";
    case "UK":
      return "우크라이나어";
    case "ZH":
      return "중국어 (간체)";
    default:
      return "언어 선택";
  }
}
function toggleDropdown2() {
  document.getElementById("langDropdown2").classList.toggle("show");
  document.getElementById("langDropdown").classList.remove("show");
}

function selectLang2(langCode) {
  document.getElementById("langDropdown2").classList.remove("show");
  document.getElementById("dropdown-btn2").innerText =
    getLanguageName2(langCode);
  document.getElementById("targetLang").value = langCode;
}

function getLanguageName2(langCode) {
  switch (langCode) {
    case "AR":
      return "아랍어";
    case "BG":
      return "불가리아어";
    case "CS":
      return "체코어";
    case "DA":
      return "덴마크어";
    case "DE":
      return "독일어";
    case "EL":
      return "그리스어";
    case "EN-US":
      return "영어(영국식)";
    case "EN-GB":
      return "영어(미국식)";
    case "ES":
      return "스페인어";
    case "ET":
      return "에스토니아어";
    case "FI":
      return "핀란드어";
    case "FR":
      return "프랑스어";
    case "HU":
      return "헝가리어";
    case "ID":
      return "인도네시아어";
    case "IT":
      return "이탈리아어";
    case "JA":
      return "일본어";
    case "KO":
      return "한국어";
    case "LT":
      return "리투아니아어";
    case "LV":
      return "라트비아어";
    case "NB":
      return "노르웨이 보크몰";
    case "NL":
      return "네덜란드어";
    case "PL":
      return "폴란드어";
    case "PT-BR":
      return "포르투갈어(브라질)";
    case "PT-PT":
      return "포르투갈어";
    case "RO":
      return "루마니아어";
    case "RU":
      return "러시아어";
    case "SK":
      return "슬로바키아어";
    case "SL":
      return "슬로베니아어";
    case "SV":
      return "스웨덴어";
    case "TR":
      return "터키어";
    case "UK":
      return "우크라이나어";
    case "ZH":
      return "중국어 (간체)";
    default:
      return "언어 선택";
  }
}
/*--------------------------------- */
// 챗봇

//이전 질문 및 답변을 저장해 놓을 빈 배열 객체
//이전 질문과 답변을 저장해놓고 gpt에 물어봐야 더 정확한 답변이 옵니다.
let gpt_token = [];
//GPT에 답변을 요구하는 ajax 함수
function gpt() {
	$.ajax({
		// Open API에서 제공하는 GPT 사용을 위한 url 주소
	    url: 'gpttest',
	    type: 'POST',
		dataType : "json",
		contentType: 'application/json', // 해당 옵션이 있어야 json형식을 controller에서 깨지지 않고 받을 수 있음
	 	// ajax는 데이터를 보낼 때 String만 보내기 때문에 Json객체를 String으로 변환해서 보내야합니다.
	    data: JSON.stringify({
	        model: "gpt-3.5-turbo",
	        messages: gpt_token
	    }),
	    success: function(data) {
	    	// 챗봇이 응답 데이터에 
	    	let message = data.choices[0].message.content
	    	test = { role: "assistant", content: message }
	    	gpt_token.push(test)
	    	addMessage('챗봇', message);
	    },
	    error: function(xhr, status, error) {
	        console.error('Error:', error);
	    }
	});
}

//div에 메세지 추가
function addMessage(sender, message) {
 // 새로운 div 생성
 const messageElement = document.createElement('div');
 // 생성된 요소에 클래스 추가
 if(sender === '챗봇'){
 	messageElement.className = 'message botusermessage';
 }else if (sender === 'user') {
 	messageElement.className = 'message usermessage';
	
}
  // 채팅 메시지 목록에 새로운 메시지 추가
 messageElement.textContent = sender + ": " + message;
 $("#chat-messages").prepend(messageElement);
}

$(document).ready(function() {
	//전송 버튼 클릭 이벤트 처리
	$("#user-input button").on('click', function(e) {
		// 사용자가 입력한 메시지
	    let message = $("#user-input input").val();
	    // 메시지가 비어있으면 리턴
	    if (message.length === 0) return;
	    // 사용자 메시지 화면에 추가
	    addMessage('user', message);
	    $("#user-input input").val("");
	    test = { role: "user", content: message }
	    gpt_token.push(test)
	    //ChatGPT API 요청후 답변을 화면에 추가
	    gpt();
	});
	
	// 사용자 입력 필드에서 Enter 키 이벤트를 처리
	$("#user-input input").on('keydown', function(e) {
		if (e.key === 'Enter') {
			$("#user-input button").click();
		}
	});
	
})