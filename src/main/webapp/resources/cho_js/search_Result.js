let areaCode = "";
let areaName = "";
$(document).ready(function () {
  getList();
  search(1); // 초기 검색은 첫 페이지로 시작

  // 지역 바뀔 때마다 시군구 가져오는 ajax 실행
  $("#areaCodes").change(function () {
    getList();
  });

  // 서치 버튼 누르면 실행
  $(".SearchButton").click(function () {
    search(1); // 검색 실행하면서 첫 페이지로 이동
  });

  // 타이틀 검색 엔터 처리
  document
    .querySelector(".areaSearchForm")
    .addEventListener("keypress", function (e) {
      if (e.key === "Enter") {
        e.preventDefault(); // 기본 제출 동작 방지
        document.querySelector(".SearchButton").click(); // SearchButton 클릭
      }
    });
  // 페이지 이동 엔터 처리
  document
    .querySelector(".board-list-paging")
    .addEventListener("keypress", function (e) {
      if (e.target.classList.contains("pageMove")) {
        if (e.key === "Enter") {
          e.preventDefault(); // 기본 제출 동작 방지
          document.querySelector(".pageMoveButton").click(); // SearchButton 클릭
        }
      }
    });

  document
    .querySelector(".board-list-paging")
    .addEventListener("input", function (e) {
      if (e.target.classList.contains("pageMove")) {
        // 입력된 값에서 숫자가 아닌 문자를 제거
        let inputValue = e.target.value.replace(/\D/g, "");
        // input 요소의 값을 업데이트
        e.target.value = inputValue;
      }
    });
});

//  찜버튼 누르기 옵션 이벤트 처리
$(document).on("click", ".heart-state", function (e) {
  let contentid = $(this).data("place_contentid");
  let userLogin = document.getElementById("userLogin").value;

  if (userLogin !== null && userLogin === "ok") {
    if ($(this).hasClass("wish-added")) {
      placeWishRemove(this, contentid);
    } else {
      placeWishadd(this, contentid);
    }
  } else {
    alert("찜하기를 하시려면 로그인을 해주세요.");
  }
});

// 장소 찜하기
function placeWishadd(tag, contentid) {
  $.ajax({
    url: "placeWishAdd",
    type: "post",
    data: { contentid: contentid },
    dataType: "text",
    success: function (data) {
      $(tag).addClass("wish-added");
      $(tag).html('<img src="resources/ko_images/heart_on3.png" width="30px;>');
      alert("좋아요를 눌렀습니다.");
      if (areaCode === "" || areaName === "") {
        areaCode = "1";
        areaName = "서울";
      }
      search(getCurrentPage());
    },
    error: function () {
      alert("실패");
    },
  });
}

// 장소 찜제거
function placeWishRemove(tag, contentid) {
  $.ajax({
    url: "placeWishRemove",
    type: "post",
    data: {
      contentid: contentid,
    },
    dataType: "text",
    success: function (data) {
      $(tag).html(
        '<img src="resources/ko_images/heart_off2.png" width="30px;>'
      );
      $(tag).removeClass("wish-added");
      alert("좋아요를 취소하셨습니다.");
      if (areaCode === "" || areaName === "") {
        areaCode = "1";
        areaName = "서울";
      }
      search(getCurrentPage());
    },
    error: function () {
      alert("실패");
    },
  });
}
//  경로 찜 버튼 누르기
$(document).on("click", ".heart-state2", function (e) {
  let path_post_idx = $(this).data("path_post_idx");
  let userLogin = document.getElementById("userLogin").value;
  if (userLogin !== null && userLogin === "ok") {
    if ($(this).hasClass("wish-added")) {
      pathWishRemove(this, path_post_idx);
    } else {
      pathWishadd(this, path_post_idx);
    }
  } else {
    alert("찜하기를 하시려면 로그인을 해주세요.");
  }
});

// 경로 찜하기
function pathWishadd(tag, path_post_idx) {
  $.ajax({
    url: "pathWishAdd",
    type: "post",
    data: { path_post_idx: path_post_idx },
    dataType: "text",
    success: function (data) {
      $(tag).addClass("wish-added");
      $(tag).html('<img src="resources/ko_images/heart_on3.png "width="30px;>');
      alert("좋아요를 눌렀습니다.");
      search(getCurrentPage());
    },
    error: function () {
      alert("실패");
    },
  });
}

// 장소 찜제거
function pathWishRemove(tag, path_post_idx) {
  $.ajax({
    url: "pathWishRemove",
    type: "post",
    data: {
      path_post_idx: path_post_idx,
    },
    dataType: "text",
    success: function (data) {
      $(tag).html('<img src="resources/ko_images/heart_off2.png"width="30px;>');
      $(tag).removeClass("wish-added");
      alert("좋아요를 취소하셨습니다.");
      search(getCurrentPage());
    },
    error: function () {
      alert("실패");
    },
  });
}
// 시군구 가져오기
function getList() {
  $("#sigunguCode").empty();
  let basic_option = $("<option>").val("999").text("전체");
  $("#sigunguCode").append(basic_option);
  $.ajax({
    url: "sigunguCodeList",
    method: "get",
    dataType: "json",
    data: { areaCode: $("#areaCodes").val() },
    success: function (data) {
      $.each(data.response.body.items.item, function (index, item) {
        let option = $("<option>").val(item.code).text(item.name);
        $("#sigunguCode").append(option);
      });
    },
    error: function () {
      alert("읽기 실패");
    },
  });
}
// 페이지 번호 클릭 이벤트 처리
$(document).on("click", ".pagination li.page-item", function (e) {
  e.preventDefault();
  let page = parseInt($(this).attr("data-page"));
  search(page); // 해당 페이지 검색 실행
});

// 페이지 검색 이동
$(document).on("click", ".pageMoveButton", function (e) {
  e.preventDefault();
  let totalPage = parseInt($(".totalPage").text());
  let page = $(".pageMove").val();
  if (page.trim() === "") {
    page = getCurrentPage(); // 현재 페이지 가져오기
  }
  if (page < 1) {
    page = 1;
  }
  if (page > totalPage) {
    page = totalPage;
  }
  search(page); // 해당 페이지 검색 실행
});

//  보기 갯수 이벤트 처리
$(document).on("change", "#viewLimit", function (e) {
  e.preventDefault();
  let page = getCurrentPage(); // 현재 페이지 번호 가져오기
  search(page); // search 함수 호출
});

//  정렬 이벤트 처리
$(document).on("change", "#orderOption", function (e) {
  e.preventDefault();
  let page = getCurrentPage(); // 현재 페이지 번호 가져오기
  search(page); // search 함수 호출
});

// 현재 페이지 구하는 함수
function getCurrentPage() {
  return parseInt($(".nowPage").attr("data-page"));
}

function search(page) {
  let areaCode = $("#areaCodes").val();
  let sigunguCode = $("#sigunguCode").val();
  let contentType = $("#contentTypes").val();
  let title = $(".searchTitle").val();
  let limit = $("#viewLimit").val();
  let order = $("#orderOption").val();
  let type = $("#searchType").val();
  // AJAX 요청 실행
  $.ajax({
    url: "areaSearchTourList",
    method: "post",
    dataType: "json",
    data: {
      areaCode: areaCode,
      sigunguCode: sigunguCode,
      contentType: contentType,
      page: page,
      title: title,
      limit: limit,
      order: order,
      type: type,
    },
    success: function (data) {
      $("#place_wrapper").empty();
      // 총 갯수 표시
      getTotalRecord(data.paging);
      // 검색된 배열의 JSON 요소들을 반복하면서 처리
      for (let i = 0; i < data.searchVOList.length; i++) {
        if (data.type === "1") {
          let place = data.searchVOList[i];
          addPlace(place);
        } else {
          let path = data.searchVOList[i];
          addPath(path);
        }
      }
      // 페이징 처리
      updatePagination(data.paging);
    },
    error: function () {},
  });
}

function getTotalRecord(paging) {
  let totalRecordHtml = "검색 결과(" + paging.totalRecord + ")";

  $("#resultCount").html(totalRecordHtml);
}

// 페이징 처리 함수
function updatePagination(paging) {
  let content = "";
  content +=
    '<input type="hidden" class="nowPage" data-page="' + paging.nowPage + '">';
  content += '<ol class="pagination" id="pagination">';
  if (paging.beginBlock > 1) {
    content += '<li class="page-item" data-page="' + 1 + '"> 1 </li>';
  }
  if (paging.beginBlock > 1) {
    content +=
      '<li class="page-item" data-page="' +
      (paging.beginBlock - 1) +
      '"> ... </li>';
  }

  // 페이지 번호를 표시할 개수
  const pageNumberDisplay = 5;

  // 현재 페이지 번호를 기준으로 앞뒤로 표시할 페이지 개수 계산
  let startPage = Math.max(
    paging.nowPage - Math.floor(pageNumberDisplay / 2),
    1
  );
  let endPage = Math.min(startPage + pageNumberDisplay - 1, paging.totalPage);

  // 보정된 시작 페이지 번호 계산
  startPage = Math.max(endPage - pageNumberDisplay + 1, 1);

  // 중앙에 오도록 현재 페이지 번호를 위치시키기 위한 변수 설정
  let centerIndex = Math.floor(pageNumberDisplay / 2);
  // 빈 공간을 표시하는 부분 추가
  if (paging.nowPage === 1) {
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
  }
  if (paging.nowPage === 2) {
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
  }
  if (paging.nowPage === 3) {
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
  }

  // 페이지 번호를 표시하는 부분 수정
  for (let i = startPage; i <= endPage; i++) {
    if (i === paging.nowPage) {
      content +=
        '<li class="nowPageInput">' +
        '<input type="text" class="pageMove" value="' +
        i +
        '">' +
        "</li>";
    } else {
      content += '<li class="page-item" data-page="' + i + '">' + i + "</li>";
    }
  }
  if (paging.nowPage < paging.totalPage - 2) {
    if (paging.endBlock < paging.totalPage) {
      content +=
        '<li class="page-item" data-page="' +
        (paging.endBlock + 1) +
        '"> ... </li>';
    }
    if (paging.endBlock < paging.totalPage) {
      content +=
        '<li class="page-item totalPage" data-page="' +
        paging.totalPage +
        '"> ' +
        paging.totalPage +
        " </li>";
    }
  }
  if (paging.nowPage === paging.totalPage) {
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
    content += '<li class="page-item blank"> </li>';
  }
  if (paging.nowPage === paging.totalPage - 1) {
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
  }
  if (paging.nowPage === paging.totalPage - 2) {
    content += '<li class="page-item blank">  </li>';
    content += '<li class="page-item blank">  </li>';
  }

  content += "</ol>";

  $(".board-list-paging").html(content);
}

// 각 장소 정보를 HTML로 변환하여 추가하는 함수
function addPlace(place) {
  let originalTitle = place.title;
  let truncatedTitle =
    originalTitle.length > 12
      ? originalTitle.substring(0, 12) + ".."
      : originalTitle;
  let heartIcon = "";
  if (place.uheart === "1") {
    heartIcon =
      '<span class="heart-state wish-added" data-place_contentid="' +
      place.contentid +
      '">' +
      '<img src="resources/ko_images/heart_on3.png" width="30px;" >' +
      "</span>";
  } else {
    heartIcon =
      '<span class="heart-state" data-place_contentid="' +
      place.contentid +
      '">' +
      '<img src="resources/ko_images/heart_off2.png" width="30px;" >' +
      "</span>";
  }

  let placeHTML =
    '<div class="place-box" >' +
    '<div class="image-box" onclick="goProductDetail(' +
    place.contentid +
    ", " +
    place.contenttypeid +
    ')">' +
    '<img alt="' +
    place.title +
    '" src="' +
    place.firstimage +
    '">' +
    "</div>" +
    '<div class="text-box" onmouseover="showFullTitle(this, \'' +
    place.title +
    "')\" onmouseout=\"showTruncatedTitle(this, '" +
    truncatedTitle +
    '\')" onclick="goProductDetail(' +
    place.contentid +
    ", " +
    place.contenttypeid +
    ')">' +
    truncatedTitle +
    "</div>" +
    '<div class="wish-box">' +
    heartIcon +
    '<span class="heart-count">' +
    place.heart +
    "</span>" +
    "</div>" +
    "</div>";
  $("#place_wrapper").append(placeHTML);
}

// 경로
function addPath(pathPost) {
  let originalTitle = pathPost.title;
  let truncatedTitle =
    originalTitle.length > 12
      ? originalTitle.substring(0, 12) + ".."
      : originalTitle;
  let heartIcon = "";
  if (pathPost.uheart === "1") {
    heartIcon =
      '<span class="heart-state2 wish-added" data-path_post_idx="' +
      pathPost.path_post_idx +
      '">' +
      '<img src="resources/ko_images/heart_on3.png" width="30px; >' +
      "</span>";
  } else {
    heartIcon =
      '<span class="heart-state2" data-path_post_idx="' +
      pathPost.path_post_idx +
      '">' +
      '<img src="resources/ko_images/heart_off2.png" width="30px; >' +
      "</span>";
  }
  let pathPostHTML =
    '<div class="place-box" >' +
    '<div class="image-box" onclick="goPathReviewDetail(' +
    pathPost.path_post_idx +
    ')">' +
    '<img alt="' +
    pathPost.title +
    '" src="resources/rc_main_img/' +
    pathPost.firstimage +
    '">' +
    "</div>" +
    '<div class="text-box" onmouseover="showFullTitle(this, \'' +
    pathPost.title +
    "')\" onmouseout=\"showTruncatedTitle(this, '" +
    truncatedTitle +
    '\')" onclick="goProductDetail(' +
    pathPost.path_post_idx +
    ", " +
    pathPost.r_contenttypeid +
    ')">' +
    truncatedTitle +
    "</div>" +
    '<div class="wish-box">' +
    heartIcon +
    '<span class="heart-count">' +
    pathPost.heart +
    "</span>" +
    "</div>" +
    "</div>";
  $("#place_wrapper").append(pathPostHTML);
}

// 줄인 제목 전부 보기
function showFullTitle(element, originalTitle) {
  element.textContent = originalTitle;
}

// 기본 제목으로 되돌리기
function showTruncatedTitle(element, truncatedTitle) {
  element.textContent = truncatedTitle;
}
// 장소 디테일
function goProductDetail(contentid, contenttypeid) {
  location.href =
    "ko_detail.do?contentid=" + contentid + "&contenttypeid=" + contenttypeid;
}
// 경로 디테일
function goPathReviewDetail(contentid, contenttypeid) {
  location.href = "pathReviewDetail?path_post_idx=" + path_post_idx;
}
