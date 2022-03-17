<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    "<form id='answer-form'>" +
                        "<textarea id='content'></textarea>" +
                        "<div id='answer-footer'>" +
                            "<span id='imageUpload-button'>IMAGE</span>&nbsp;" +
                            "<span id='answerPost-button'>POST</span>&nbsp;" +
                            "<span id='postCancel-button'>CANCEL</span>&nbsp;" +
                        "</div>" +
                    "</form>" +
                "</div>"
                <%--"<%@ include file='/WEB-INF/views/testForm.jsp' %>"--%>
            )

            initEditor("#content")

        })
    </script>
</body>
</html>
