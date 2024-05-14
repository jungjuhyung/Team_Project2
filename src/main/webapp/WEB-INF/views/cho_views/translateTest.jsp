<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.dropdown {
    position: relative;
    display: inline-block;
}

#dropdown-btn {
    background-color: #f1f1f1;
    border: none;
    cursor: pointer;
    padding: 10px;
    width: 150px;
    text-align: center;
}
#dropdown-btn2 {
    background-color: #f1f1f1;
    border: none;
    cursor: pointer;
    padding: 10px;
    width: 150px;
    text-align: center;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
    z-index: 1;
}
.dropdown-content.show {
	display: block; 
}
.dropdown-item {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    cursor: pointer;
}

.dropdown-item:hover {
    background-color: #f1f1f1;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function translating() {
		let sourceText = $("#sourceText").val();
		let targetLang = $("#targetLang").val();
		let inputLang = $("#inputLang").val();
		$.ajax({
			url : "Translating",
			type : "post",
			data : { 
				sourceText: sourceText,
				targetLang: targetLang,
				inputLang: inputLang
			},
			dataType : "json",
			success : function(data) {
				console.log(data)
			},
			error : function() {
				alert("실패");
			}
		});
	}
	function toggleDropdown() {
	    document.getElementById("langDropdown").classList.toggle("show");
	}

	function selectLang(langCode) {
	    document.getElementById("langDropdown").classList.remove("show");
	    document.getElementById("dropdown-btn").innerText = getLanguageName(langCode);
	    document.getElementById("inputLang").value = langCode;
	}

	function getLanguageName(langCode) {
	    switch (langCode) {
	        case "AR": return "아랍어";
	        case "BG": return "불가리아어";
	        case "CS": return "체코어";
	        default:
	            return "언어 선택";
	    }
	}
	function toggleDropdown2() {
	    document.getElementById("langDropdown2").classList.toggle("show");
	}

	function selectLang2(langCode) {
	    document.getElementById("langDropdown2").classList.remove("show");
	    document.getElementById("dropdown-btn2").innerText = getLanguageName2(langCode);
	    document.getElementById("targetLang").value = langCode;
	}

	function getLanguageName2(langCode) {
	    switch (langCode) {
	        case "AR": return "아랍어";
	        case "BG": return "불가리아어";
	        case "CS": return "체코어";
	        case "EN-US" : return "영어";
	        default:
	            return "언어 선택";
	    }
	    
	}
</script>
</head>
<body>
	<div>
		<div class="dropdown">
		    <div id="dropdown-btn" onclick="toggleDropdown()">언어 감지</div>
		    <div class="dropdown-content" id="langDropdown">
		        <div class="dropdown-item" onclick="selectLang('null')">언어감지</div>
		        <div class="dropdown-item" onclick="selectLang('AR')">아랍어</div>
		        <div class="dropdown-item" onclick="selectLang('BG')">불가리아어</div>
		        <div class="dropdown-item" onclick="selectLang('CS')">체코어</div>
		        <!-- 다른 언어들을 추가합니다 -->
		        <!-- 다른 언어들을 추가합니다 -->
		    </div>
		</div>
		<div class="dropdown">
		    <div id="dropdown-btn2" onclick="toggleDropdown2()">한국어</div>
		    <div class="dropdown-content" id="langDropdown2">
		        <div class="dropdown-item" onclick="selectLang2('AR')">아랍어</div>
		        <div class="dropdown-item" onclick="selectLang2('BG')">불가리아어</div>
		        <div class="dropdown-item" onclick="selectLang2('CS')">체코어</div>
		        <div class="dropdown-item" onclick="selectLang2('EN-US')">영어</div>
		        <!-- 다른 언어들을 추가합니다 -->
		    </div>
		</div>
		<br>
		<input type="hidden" id = "targetLang">
		<input type="hidden" id = "inputLang">
		<input type="text" id = "sourceText" placeholder="텍스트 입력">
		<input type="text" class = "transText" placeholder="번역">
	</div>
	<input type="button" onclick="translating()" value = "번역">
	
</body>
</html>