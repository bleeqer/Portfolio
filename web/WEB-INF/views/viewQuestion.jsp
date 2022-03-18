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
    TITLE : ${post.title}
    <br>
    CONTENT : ${post.content}
    <br>
    WRITER : ${post.writer}
    <br>
    <a href="/question/edit/${post.quesNo}">EDIT</a>
    <a href="/question/delete/${post.quesNo}">DELETE</a>
    <br>
    <span id="answer-button">ANSWER</span>
    <div id="answer-container">

    </div>

    <%@ include file="/WEB-INF/views/modals/questionForm.jsp" %>

    <script>
        async function initEditor (selectorID) {

            await tinymce.init({
                selector: selectorID,
                menubar: false,
                statusbar: false,
                toolbar: false,
                setup: function(editor) {

                    editor.on('init', function(e) {
                        console.log("editor initialized")
                    })
                }
            })
        }
    </script>

    <script>

        const header = $("meta[name='_csrf_header']").attr("content")
        const token = $("meta[name='_csrf']").attr("content")

        $("#answer-button").on("click", function () {

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
                            "<span id='answerPost-button'>POST</span>&nbsp;" +
                            "<span id='postCancel-button'>CANCEL</span>&nbsp;" +
                        "</div>" +
                        "<input type='file' multiple='multiple' name='image' id='image' style='display: none;' accept='image/*'>" +
                    "</form>" +
                "</div>"
            )

            $('#upload-button').on('click', function () {

                $('#image').trigger('click')

            })

            $('#answerPost-button').on('click', function () {

                $('#answer-form').submit()

            })

            $('#postCancel-button').on('click', function () {

                $('#answer-container').empty()
                tinymce.remove()
                $("#answer-button").css("pointer-events", "auto")
                $("#answer-button").css("color", "black")
            })

            $.getScript('/js/imageUpload.js')

            initEditor("#content")

        })


        $(".edit-button").click(async function (e) {

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

    </script>

</body>
</html>
