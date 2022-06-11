
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
    $('#signUp-form').submit()

})