
$('#profile-image-upload-button').click(function () {

    $('#image').trigger('click')
})

// 이미지 업로드 함수. image input 변경 시 실행됨
$('#image').on("change", function () {

    // input 태그의 파일 데이터
    const files = this.files

    // ajax submit용 form data
    const formData = new FormData()

    // input 태그 파일을 데이터 순회하며 form data에 추가
    // 첫번째 스트링 인자('image')는 서버에서 multipart file의 파라미터명으로 쓰이므로 주의
    for (let i=0; i<files.length; i++) {

        formData.append('image', files[i])
    }

    // ajax 호출
    $.ajax({
        // 요청 URL
        url: "/file/upload",

        // 파일 전송 시
        enctype: "multipart/form-data",

        // data의 스트링화(stringify) 방지
        processData: false,

        // contentType의 default 값 false
        contentType: false,

        // 전송할 데이터
        data: formData,

        // 데이터 전송 방식
        type: 'POST',

        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },

        // ajax 요청 성공 시 콜백함수
        success: function (path) {

            $('#profile-upload-image').attr('src', '/uploadedImages' + path)

        },
        error: function () {
            alert("파일 유형을 확인해주세요.")
        }
    })
})

$('#register-button').click(function () {

    $('#photoPath').val($('#image').val())
    if (isValid()) {
        $('#signUp-form').submit()

    }


})

$('#login-button').click(function (e) {

    e.preventDefault()

    $.ajax({
        type: 'POST',
        url: '/user/login',
        data: $('#login-form').serialize(),
        context: this,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        success: function (success) {

            if (success.message === 'success') {

                window.location.href = '/'
            }
        },
        error: function (error) {

            $('#login-message .message').text(error.responseJSON.message)
            $('#login-message').fadeOut(50).fadeIn(50)

        }
    })
})


function isValid(){

    const nameRegExp = RegExp(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g)
    const emailRegExp = RegExp(/^([\w\.\_\-])*[a-zA-Z\d]+([\w\.\_\-])*([a-zA-Z\d])+([\w\.\_\-])+@([a-zA-Z\d]+\.)+[a-zA-Z\d]{2,8}$/)
    const pwRegExp = RegExp(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/)
    const occupationRegExp = RegExp(/[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi)

    const name = $('#register-name').val()
    const email = $('#register-email').val()
    const pw = $('#register-password').val()
    const occupation = $('#register-occupation').val()

    if (name === '' || typeof(name) === 'undefined') {
        $('#signUp-message .message').text('이름을 입력해주세요.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    } else if (nameRegExp.test(name)) {
        $('#signUp-message .message').text('이름란에 특수문자는 사용하실 수 없습니다.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    }

    if (email === '' || typeof(email) === 'undefined') {
        $('#signUp-message .message').text('이메일을 입력해주세요.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    } else if (!emailRegExp.test(email)) {
        $('#signUp-message .message').text('올바른 이메일이 아닙니다.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
      return false

    }

    if (pw === '' || typeof(pw) === 'undefined') {
        $('#signUp-message .message').text('비밀번호를 입력해주세요.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    } else if (!pwRegExp.test(pw)) {
        $('#signUp-message .message').text('비밀번호는 영문과 숫자의 조합으로 8~20자리 입니다.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    }

    if (occupation === '' || typeof(occupation) === 'undefined') {
        $('#signUp-message .message').text('직업을 입력해주세요.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    } else if (occupationRegExp.test(occupation)) {
        $('#signUp-message .message').text('직업란에 특수문자는 사용하실 수 없습니다.')
        $('#signUp-message').fadeOut(50).fadeIn(50)
        return false

    }

    return true
}



