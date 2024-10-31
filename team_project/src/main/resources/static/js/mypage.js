/*
$(document).ready(function() {

    let isLoggedIn = sessionStorage.getItem('isLoggedIn') === 'true';
    function updateUIBasedOnLoginStatus() {
        if (isLoggedIn) {
            $('#loginButton').hide();
            $('#signupButton').hide();
            $('.logout').show();
        } else {
            $('#loginButton').show();
            $('#signupButton').show();
            $('.logout').hide();
        }
    }

    updateUIBasedOnLoginStatus();

    $('#loginButton').click(function() {
        isLoggedIn = true;
        sessionStorage.setItem('isLoggedIn', 'true');
        updateUIBasedOnLoginStatus();
    });

    $('.logout button').click(function() {
        isLoggedIn = false;
        sessionStorage.setItem('isLoggedIn', 'false');
        updateUIBasedOnLoginStatus();
    });

    $('#editButton').click(function() {
        $('#userInfoForm input').prop('disabled', false);
        $(this).hide();
        $('#userInfoForm button[type="submit"]').removeClass('hidden');
    });

    $('#togglePassword').click(function() {
        const passwordField = $('#passwordField');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        $(this).text(type === 'password' ? '보이기' : '숨기기');
    });
});
*/



$(document).ready(function() {
    // 페이지 로드 시 로그인 상태를 확인합니다.
    updateUIBasedOnLoginStatus();

    function updateUIBasedOnLoginStatus() {
        // 세션에 사용자 정보가 저장되어 있는지 확인
        const userInfo = sessionStorage.getItem('userInfo');
        let isLoggedIn = userInfo !== null; // userInfo가 null이 아니면 로그인 상태

        if (isLoggedIn) {
            $('#loginButton').hide();
            $('#signupButton').hide();
            $('.logout').show();
        } else {
            $('#loginButton').show();
            $('#signupButton').show();
            $('.logout').hide();
        }
    }

    // 로그인 버튼 클릭 시 로그인 상태를 설정하고 사용자 정보를 세션에 저장
/*    $('#loginButton').click(function() {
        // 여기에 사용자 정보를 예시로 설정합니다 (실제 사용자 정보로 대체해야 함).
        const userInfo = { id: 'user123', name: '사용자 이름' };
        sessionStorage.setItem('userInfo', JSON.stringify(userInfo)); // 사용자 정보를 JSON 문자열로 저장


        updateUIBasedOnLoginStatus();  // UI 업데이트
    });*/

    // 로그아웃 버튼 클릭 시 로그인 상태를 해제하고 UI 업데이트
    $('.logout button').click(function() {
        sessionStorage.removeItem('userInfo');  // 세션에서 사용자 정보 제거
        updateUIBasedOnLoginStatus();  // UI 업데이트
    });

    $('#editButton').click(function() {
        $('#userInfoForm input').prop('disabled', false);
        $(this).hide();
        $('#userInfoForm button[type="submit"]').removeClass('hidden');
    });

    $('#togglePassword').click(function() {
        const passwordField = $('#passwordField');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        $(this).text(type === 'password' ? '보이기' : '숨기기');
    });
});