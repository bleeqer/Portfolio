<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/15/22
  Time: 6:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String realPath = request.getSession().getServletContext().getRealPath("uploadedImages"); %>

<!DOCTYPE HTML>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/tinymce/tinymce.min.js"></script>
    <script src="/js/tinymce/jquery.tinymce.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
    <sec:csrfMetaTags/>
</head>

<body>
<%--    <sec:authentication property="principal.username">--%>
<sec:authentication property="principal.username" var="currentUserName"/>

    TITLE : <div id="detail-title">${post.title}</div>
    <br>
    CONTENT : <div id="detail-content">${post.content}</div>
    <br>
    WRITER : <div id="detail-writer">${post.writer}</div>
    <br>
    <br>
    REGDATE : <div id="detail-writer">${post.regDate}</div>
    <br>
    <c:if test="${currentUserName == post.writer}">
        <span id="edit-button" data-id="${post.quesNo}">EDIT</span>
        <span id="delete-button" data-id="${post.quesNo}">DELETE</span>
    </c:if>
    <br>
    <span id="answer-button">ANSWER</span>
    <div id="answer-container">

    </div>

    <%@ include file="/WEB-INF/views/modals/questionForm.jsp" %>

    <script>
        async function initEditor () {

            await tinymce.init({
                selector: '#content',
                menubar: false,
                statusbar: false,
                toolbar: false,
                // setup: function (editor) {
                //     editor.on('change', function () {
                //         editor.save()
                //     })
                // },
            })
        }
    </script>

    <script>

        const header = $("meta[name='_csrf_header']").attr("content")
        const token = $("meta[name='_csrf']").attr("content")


        $("#answer-button").on("click", function () {

            // answer-button 최초 클릭 시 버튼 비활성화
            $("#answer-button").css("pointer-events", "none")
            $("#answer-button").css("color", "grey")

            $("#answer-container").append(
                "<div id='answer'>" +
                    "<div id='user-profile'>사용자</div>" +
                    "<form id='answer-form' action='/answer/create'>" +
                        "<input id=quesNo value=${post.quesNo} type='hidden'>" +
                        "<input id=writer value=${post.writer} type='hidden'>" +
                        "<textarea id='content'></textarea>" +
                        "<div id='answer-footer'>" +
                            "<span id='upload-button'>IMAGE</span>&nbsp;" +
                            "<span id='answerSubmit-button'>POST</span>&nbsp;" +
                            "<span id='postCancel-button'>CANCEL</span>&nbsp;" +
                        "</div>" +
                        "<input type='file' multiple='multiple' name='image' id='image' style='display: none;' accept='image/*'>" +
                    "</form>" +
                "</div>"
            )

            $('#answerSubmit-button').on('click', function () {

                $('#answer-form').submit()
                $('#answer-container').empty()
                tinymce.remove()
            })

            $('#postCancel-button').on('click', function () {

                $('#answer-container').empty()
                tinymce.remove()

                // 답변 작성 취소 시 answer-button 재활성화
                $("#answer-button").css("pointer-events", "auto")
                $("#answer-button").css("color", "black")
            })

            $.getScript('/js/imageUpload.js')

            initEditor()

        })


        $("#edit-button").click(async function (e) {

            e.preventDefault()

            const quesNo = $(this).data('id')

            $("#question-form").attr("action", "/question/edit/")

            await $("#modal-question").modal("show")

            await initEditor()

            $.getJSON('/question/edit/' + quesNo, function (question) {
                $("#quesNo").val(question.quesNo)
                $("#title").val(question.title)
                tinymce.activeEditor.setContent(question.content)
                $("#writer").val(question.writer)
            })
        })

        const observer = new MutationObserver(function() {

            if ($(".modal").css("display") === "none") {

                // 텍스트 에디터에 저장된 값 지우기
                tinymce.activeEditor.setContent("")

                // 기존 텍스트 에디터 객체 지우기
                tinymce.remove()

                // 인풋 태그에 저장된 값 지우기
                $("input").val("")

            }
        })

        // 변경 감시 대상
        const target = document.getElementById('modal-question');

        // style 속성 감시
        observer.observe(target, { attributes : true, attributeFilter : ['style'] });

    </script>

</body>
</html>
