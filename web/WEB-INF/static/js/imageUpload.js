
// 이미지 업로드 함수. image input 변경 시 실행됨
$("#image").on("change", function () {

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

        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },

        // 전송할 데이터
        data: formData,

        // 데이터 전송 방식
        type: "POST",

        // ajax 요청 성공 시 콜백함수
        success: function (paths) {

            let tags = ''

            for (const path of paths) {

                // 이미지 태그 클릭하면 에러 발생함 나중에 삭제버튼 추가하기
                tags = tags + '<div><img class="inserted-image" alt="photo" src="' + path + '" style="width: 200px; height: 150px;" /></div>'
            }

            tinymce.activeEditor.insertContent(tags)

            // 사진 클릭 시 태그 삭제 (후에 x 버튼으로 대신할 것)
            $("#content_ifr").contents().find(".inserted-image").bind('click', function () {
                this.remove()
            })

        },
        error: function () {
            alert("이미지 파일이 아닙니다.")
        }
    })
})