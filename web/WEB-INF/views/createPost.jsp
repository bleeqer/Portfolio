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
</head>
<body>
    <form action="/board/create" method="POST" enctype="multipart/form-data">

        <%--    Editor로 사용할 태그    --%>
        <textarea name="content" id="content" rows="50" cols="30"></textarea>

        <%--      이미지 업로드 input 태그(숨김)      --%>
        <input type="file" multiple="multiple" name="image" id="image" style="display: none;" accept="image/*">

    </form>

    <script>
           function loadFile(input) {
               var file = input.files[0];

               console.log($('#textarea').caret());
               // 새 img 태그 생성
               var newImage = document.createElement("img");

               // 이미지 태그 클래스 img로 지정
               newImage.setAttribute("class", 'img');

               // 이미지 source 가져오기
               newImage.src = URL.createObjectURL(file);


           }

    </script>

    <script type="text/javascript">

        // text editor 초기화
        tinymce.init({
            selector: "#content",
            menubar:false,
            statusbar: false,
            toolbar: "custom_image", // 커스텀 버튼 이름

            // 셋업 시 콜백함수
            setup: function(editor) {

                // 커스텀 버튼 생성 (tinymce 5 버전, 첫번째 인자는 toolbar에 추가할 때 사용되는 버튼의 이름)
                editor.ui.registry.addButton('custom_image', {
                    title: 'Insert Image',
                    icon: 'image',

                    // 버튼 동작 시
                    onAction: triggerTag // input 태그 작동 함수
                })
            }}
        )

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
</body>
</html>
