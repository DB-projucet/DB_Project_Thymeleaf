$(document).ready(function() {
    $("#editBtn").click(function() {
        $("input").prop("disabled", function(i, v) { return !v; }); // 입력 필드의 disabled 속성 전환
        $(this).next("button").toggleClass("hidden"); // 저장 버튼 보이기/숨기기
        $(this).text($(this).text() === '수정' ? '취소' : '수정'); // 버튼 텍스트 전환
    });
});