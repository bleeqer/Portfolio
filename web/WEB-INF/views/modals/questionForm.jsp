<div id="modal-question" class="modal">

    <form id="question-form" action="" method="POST" enctype="multipart/form-data">

        <input type="hidden" id="bno" name="bno">

        <input type="text" id="title" name="title" onchange="test()">

        <%--    Editor로 사용할 태그    --%>
        <textarea name="content" id="content" style="width: 100%; height: 100%;"></textarea>
        <input type="text" id="writer" name="writer" placeholder="">

        <div id="form-footer">
            <span id="upload-button">Image</span>
            <button type="submit" id="submit-button" class="btn">Submit</button>
        </div>
        <%--      이미지 업로드 input 태그(숨김)      --%>
        <input type="file" multiple="multiple" name="image" id="image" style="display: none;" accept="image/*">
    </form>
</div>

<script>

    // 이미지 업로드 버튼 클릭 시 image input 태그 클릭 작동
    $("#upload-button").click(function () {
        $("#image").trigger("click")
    })

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

            // 전송할 데이터
            data: formData,

            // 데이터 전송 방식
            type: "POST",

            // ajax 요청 성공 시 콜백함수
            success: function (imgs) {

                // 읽어온 이미지를 에디터 Content에 img 태그로 추가
                for (const [idx, img] of imgs.entries()) {
                    console.log(idx)
                    tinymce.activeEditor.insertContent('<img class="insertedImg" alt="photo" src="/uploadedImages/' + img.uploadPath + '" style="width: 200px; height: 150px;" data-idx=' + idx + ' />')
                }
            }
        })
    })

    $(document).on('click', '.insertedImg', function () {
        console.log("img clicked")
        // this.remove()
    })
</script>


