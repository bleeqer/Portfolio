<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/13/22
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/tinymce/tinymce.min.js"></script>

    <!-- jQuery Modal -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

</head>
<body>
    <h2>게시판</h2>
    <table>
        <tr>
            <th>BNO</th>
            <th>TITLE</th>
            <th>WRITER</th>
            <th>DATE</th>
            <th>VIEWS</th>
            <th>OPTION</th>
        </tr>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.bno}</td>
                <td><a href="/board/${post.bno}">${post.title}</a></td>
                <td>${post.writer}</td>
                <td>${post.regDate}</td>
                <td>${post.viewCnt}</td>
                <td><a href="#modal-question" class="editBtn" data-id="${post.bno}">EDIT</a></td>
            </tr>
        </c:forEach>
    </table>

    <label for="test"></label>
    <input id="test" type="reset">

    <%@ include file="/WEB-INF/views/modals/questionForm.jsp" %>

    <script>

        async function initEditor () {

            await tinymce.init({
                selector: "#content",
                menubar: false,
                statusbar: false,
                toolbar: false,
                height: $("#modal-question").height() - $("title").height(),
            })
        }

        const observer = new MutationObserver(function() {

            if ($("#modal-question").css("display") === "none") {
                tinymce.activeEditor.setContent("")
                tinymce.remove()
            }
        })

        // 변경 감시 대상
        const target = document.getElementById('modal-question');

        // style 속성 감시
        observer.observe(target, { attributes : true, attributeFilter : ['style'] });





    </script>

    <script>

        $(".editBtn").click(async function (e) {
            e.preventDefault()
            var bno = $(this).data('id')
            await $("#modal-question").modal("show")
            await initEditor()

            $("#question-form").attr("action", "/board/edit/")

            $.getJSON('/board/edit/' + bno, function (post) {

                $("#bno").val(post.bno)
                $("#title").val(post.title)
                tinymce.activeEditor.setContent(post.content)
                $("#writer").val(post.writer)

            })
        })
    </script>
</body>
</html>
