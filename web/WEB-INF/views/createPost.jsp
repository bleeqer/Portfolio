<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/15/22
  Time: 6:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>CREATE POST</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/tinymce/tinymce.min.js"></script>



    <!-- jQuery Modal -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

</head>
<body>

<a href="#modal-question" id="ask-question" rel="modal:open">ASK QUESTION</a>
<div id="">

</div>
<div id="modal-question" class="modal">

    <form action="/board/create" method="POST" enctype="multipart/form-data">

        <input type="text" id="title" name="title" placeholder="질문을 입력해주세요...">

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

<script type="text/javascript">

    // text editor 초기화
    // tinymce.init({
    //     selector: "#content",
    //     menubar: false,
    //     statusbar: false,
    //     toolbar: false,
    //
    // })


    const triggerTag = function () {

        // 이미지 업로드용 input 태그
        const image = $("#image")

        // input 태그 클릭 작동
        image.trigger("click")

        image.on('change', readImage)
    }

    const readImage = function () {

        // input 태그에 선택된 파일 가져오기
        const files = this.files

        for (let i = 0; i < this.files.length; i++) {

            const file = files[i]

            // fileReader 객체 생성
            const reader = new FileReader()

            // fileReader로 file의 url 읽어오기
            reader.readAsDataURL(file)

            // 읽어오기 동작 성공 시 실행
            reader.addEventListener("load", insertImg)
        }


    }

    const insertImg = function (e) {

        // 읽어온 이미지 결과
        const image = e.target.result

        // 읽어온 이미지를 에디터 Content에 img 태그로 추가
        tinymce.activeEditor.insertContent('<img alt="photo" src="' + image + '" style="width: 800px; height: 500px;"/>')

    }
</script>
<script>


    const observer = new MutationObserver( async function() {

            if ($("#modal-question").css("display") === "inline-block") {

                await tinymce.init({
                    selector: "#content",
                    menubar: false,
                    statusbar: false,
                    toolbar: false,
                    height: $("#modal-question").height() - $("title").height(),
                    // width: $("#modal-question").width()
                })
                tinymce.activeEditor.setContent("<p>하이<p> <img src='' alt='no image'/>")

            } else {
                tinymce.activeEditor.setContent("")
                tinymce.remove()
            }
    })

    // 변경 감시 대상
    const target = document.getElementById('modal-question');

    // style 속성 감시
    observer.observe(target, { attributes : true, attributeFilter : ['style'] });

    $('#upload-button').on("click", triggerTag)

    $("#submit-button").on("click", function() {
        const theContent = tinymce.activeEditor.getContent()
    })

</script>

</body>


<style>

<%--    #title {--%>
<%--        height: 25px;--%>
<%--        outline: none;--%>
<%--        border: 0;--%>
<%--        border-bottom: 1px solid gainsboro;--%>
<%--        color: black;--%>
<%--        font-size: 18px;--%>
<%--        width: 100%;--%>
<%--    }--%>

<%--    ::placeholder {--%>
<%--        color: gainsboro;--%>
<%--    }--%>

<%--&lt;%&ndash;  텍스트 에디터 보더 없애기  &ndash;%&gt;--%>
<%--    .tox-tinymce {--%>
<%--        /*border: none;*/--%>
<%--    }--%>

<%--    .btn {--%>
<%--        background-color: white;--%>
<%--        border-radius: 15px;--%>
<%--        color: #19232e;--%>
<%--        border: beige solid 0.5px;--%>

<%--    }--%>

    #modal-question {
        height: 600px;
        width: 800px;
    }

<%--    #form-footer {--%>
<%--        width: 100%;--%>
<%--        display: flex;--%>
<%--        justify-content: space-between;--%>
<%--    }--%>
</style>
</html>
