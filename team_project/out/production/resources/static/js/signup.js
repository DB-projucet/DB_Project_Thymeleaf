function validateId() {
    const memId = document.getElementById('memId').value;
    const idError = document.getElementById('idError');

    if (memId.length < 5 || memId.length > 10 || !memId.match(/^[a-z0-9!@#$%^&*()_+=-]+$/)) {
        idError.textContent = "ID는 5~10자 사이의 영문 소문자, 숫자 및 특수문자만 허용됩니다.";
        idError.style.display = "block";
    } else {
        idError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validatePassword() {
    const memPwd = document.getElementById('memPwd').value;
    const pwdError = document.getElementById('pwdError');

    if (memPwd.length < 8 || memPwd.length > 16 || !memPwd.match(/^[a-zA-Z0-9!@#$%^&*()_+=-]+$/)) {
        pwdError.textContent = "비밀번호는 8~16자 사이의 영문 대/소문자, 숫자 및 특수문자만 허용됩니다.";
        pwdError.style.display = "block";
    } else {
        pwdError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validateName() {
    const memName = document.getElementById('memName').value;
    const nameError = document.getElementById('nameError');

    if (!memName.match(/^[a-zA-Z가-힣]+$/)) {
        nameError.textContent = "이름은 영문 대/소문자와 한글만 허용됩니다.";
        nameError.style.display = "block";
    } else {
        nameError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validateEmail() {
    const memEmail = document.getElementById('memEmail').value;
    const emailError = document.getElementById('emailError');

    if (!memEmail.match(/^[a-z0-9]+@[a-z0-9]+\.[a-z]+$/)) {
        emailError.textContent = "이메일 형식이 올바르지 않습니다.";
        emailError.style.display = "block";
    } else {
        emailError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validateAddr() {
    const memAddr = document.getElementById('memAddr').value;
    const addrError = document.getElementById('addrError');

    if (memAddr.trim() === "") {
        addrError.textContent = "주소를 입력해 주세요.";
        addrError.style.display = "block";
    } else {
        addrError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validateBirth() {
    const memBirth = document.getElementById('memBirth').value;
    const birthError = document.getElementById('birthError');

    if (!memBirth.match(/^\d{4}-\d{2}-\d{2}$/)) {
        birthError.textContent = "생년월일 형식이 올바르지 않습니다. (YYYY-MM-DD)";
        birthError.style.display = "block";
    } else {
        birthError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validatePhone() {
    const memPhone = document.getElementById('memPhone').value;
    const phoneError = document.getElementById('phoneError');

    if (!memPhone.match(/^0\d{2}-\d{4}-\d{4}$/)) {
        phoneError.textContent = "휴대전화번호 형식이 올바르지 않습니다. ('-' 포함)";
        phoneError.style.display = "block";
    } else {
        phoneError.style.display = "none"; // 유효하면 메시지 숨김
    }
}

function validateShsize() {
    const memShsize = document.getElementById('memShsize').value;
    const shsizeError = document.getElementById('shsizeError');

    if (memShsize < 230 || memShsize > 290 || (memShsize % 10 !== 0)) {
        shsizeError.textContent = "발 사이즈는 230 ~ 290mm 사이여야 하며, 10단위만 입력 가능합니다.";
        shsizeError.style.display = "block";
    } else {
        shsizeError.style.display = "none"; // 유효하면 메시지 숨김
    }
}