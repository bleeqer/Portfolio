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

    <sec:csrfMetaTags/>
</head>
<body>
    TITLE : ${post.title}
    <br>
    CONTENT : ${post.content}
    <br>
    WRITER : ${post.writer}
    <br>
    <a href="/board/edit/${post.quesNo}">EDIT</a>
    <a href="/board/delete/${post.quesNo}">DELETE</a>
    <br>
    <span id="answer-button">ANSWER</span>
    <div id="answer-container">

    </div>

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

            // $("#answers").append(
            //     "<div id='answer'>" +
            //         "<div id='editor'></div>" +
            //         "<div id='answer-footer'>" +
            //             "<span id='imageUpload-button'>IMAGE</span>&nbsp;" +
            //             "<span id='post-button'>POST</span>&nbsp;" +
            //             "<span id='postCancel-button'>CANCEL</span>" +
            //         "</div>" +
            //     "</div>"
            // )

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
            })

            $.getScript('/js/imageUpload.js')

            initEditor("#content")

        })

    </script>

</body>
</html>
