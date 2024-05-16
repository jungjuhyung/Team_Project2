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
	        case "DA" : return "덴마크어";
	        case "DE" : return "독일어";
	        case "EL" : return "그리스어";
	        case "EN" : return "영어";
	        case "ES" : return "스페인어";
	        case "ET" : return "에스토니아어";
	        case "FI" : return "핀란드어";
	        case "FR" : return "프랑스어";
	        case "HU" : return "헝가리어";
	        case "ID" : return "인도네시아어";
	        case "IT" : return "이탈리아어";
	        case "JA" : return "일본어";
	        case "KO" : return "한국어";
	        case "LT" : return "리투아니아어";
	        case "LV" : return "라트비아어";
	        case "NB" : return "노르웨이 보크몰";
	        case "NL" : return "네덜란드어";
	        case "PL" : return "폴란드어";
	        case "PT" : return "포르투갈어";
	        case "RO" : return "루마니아어";
	        case "RU" : return "러시아어";
	        case "SK" : return "슬로바키아어";
	        case "SL" : return "슬로베니아어";
	        case "SV" : return "스웨덴어";
	        case "TR" : return "터키어";
	        case "UK" : return "우크라이나어";
	        case "ZH" : return "중국어 (간체)";
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
	        case "DA" : return "덴마크어";
	        case "DE" : return "독일어";
	        case "EL" : return "그리스어";
	        case "EN-US" : return "영어(영국식)";
	        case "EN-GB" : return "영어(미국식)";
	        case "ES" : return "스페인어";
	        case "ET" : return "에스토니아어";
	        case "FI" : return "핀란드어";
	        case "FR" : return "프랑스어";
	        case "HU" : return "헝가리어";
	        case "ID" : return "인도네시아어";
	        case "IT" : return "이탈리아어";
	        case "JA" : return "일본어";
	        case "KO" : return "한국어";
	        case "LT" : return "리투아니아어";
	        case "LV" : return "라트비아어";
	        case "NB" : return "노르웨이 보크몰";
	        case "NL" : return "네덜란드어";
	        case "PL" : return "폴란드어";
	        case "PT-BR" : return "포르투갈어(브라질)";
	        case "PT-PT" : return "포르투갈어";
	        case "RO" : return "루마니아어";
	        case "RU" : return "러시아어";
	        case "SK" : return "슬로바키아어";
	        case "SL" : return "슬로베니아어";
	        case "SV" : return "스웨덴어";
	        case "TR" : return "터키어";
	        case "UK" : return "우크라이나어";
	        case "ZH" : return "중국어 (간체)";
	        default:
	            return "언어 선택";
	    }
	    
	}