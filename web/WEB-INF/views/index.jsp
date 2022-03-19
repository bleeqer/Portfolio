<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/13/22
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/tinymce/tinymce.min.js"></script>
    <script src="/js/tinymce/jquery.tinymce.min.js"></script>

    <sec:csrfMetaTags/>

    <!-- jQuery Modal -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />


</head>
<body>
    <h2>게시판</h2>
<%--    <sec:authorize access="!isAuthenticated()"><span id="login-button">Login</span></sec:authorize>--%>
<%--    <span id="login-button">Login</span>--%>
<%--    <span id="register-button">Register</span>--%>
<%--    <sec:authorize access="isAuthenticated()">Logout</sec:authorize>--%>
<%--    <sec:authorize access="hasRole('ADMIN')"><a href="#">Manage Users</a></sec:authorize>--%>
    <table id="question-table">
        <span id="ask-button">ASK QUESTION</span>
        <c:forEach var="question" items="${questions}">
            <tr class="question-row">
                <td>${question.quesNo}</td>
                <td><a href="/question/${question.quesNo}">${question.title}</a></td>
                <td>${question.writer}</td>
                <td>${question.regDate}</td>
                <td>${question.viewCnt}</td>
                <td><span id="answer-button">ANSWER</span></td>
            </tr>
        </c:forEach>
    </table>


    <label for="test"></label>
    <input id="test" type="reset">

    <%@ include file="/WEB-INF/views/modals/questionForm.jsp" %>
<%--    <%@ include file="/WEB-INF/views/modals/userLoginForm.jsp" %>--%>

    <script>
        async function initEditor () {

            await tinymce.init({
                selector: "#content",
                menubar: false,
                statusbar: false,
                toolbar: false,
                height: $("#modal-question").height() - $("title").height(),
                // setup: function (editor) {
                //     editor.on('change', function () {
                //         editor.save()
                //     })
                // },
                relative_urls: false,
                // remove_script_host: false

                // setup: function(editor) {
                //
                //     editor.on('init', function(e) {
                //         console.log("editor initialized")
                //     })
                // }
            })
        }

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

    <script>

        const header = $("meta[name='_csrf_header']").attr("content")
        const token = $("meta[name='_csrf']").attr("content")

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

        $("#ask-button").click(async function (e) {

            e.preventDefault()

            // 새로운 글 생성 시 quesNo 비활성화
            $("#quesNo").attr('disabled', true)

            $("#question-form").attr("action", "/question/create/")

            await $("#modal-question").modal("show")

            await initEditor()

        })

        $("#answer-button").on('click', function() {

            $(this).parent().parent().append("<div>질문칸 삽입</div>")

        })
    </script>

    <script>

        $("#question-submit").on('click', function(e) {

            e.preventDefault()

            tinymce.activeEditor.save()

            let actionURL = $('#question-form').attr('action')

            let imgTags = $("#content_ifr").contents().find(".inserted-image")

            for (let i=0; i<Object.keys(imgTags).length - 2; i++) {

                const src = $(imgTags[i]).attr("src")

                const tag = '<input type="hidden" name="imageList[' + i + ']" value="' + src + '">'
                $("#question-form").append(tag)
            }

            const form = $("#question-form")

            // console.log(form.serialize())

            //ajax form submit
            $.ajax({
                type: "POST",
                url: actionURL,
                data: form.serialize(),
                beforeSend : function(xhr)
                {
                    xhr.setRequestHeader(header, token)
                },
                success: function(question) {
                    // console.log(question.title)
                    alert('업로드 성공')
                    $('#question-table').append(
                    '<tr class="question-row">' +
                    '<td>' + question.quesNo + '</td>' +
                    '<td><a href="/question/' + question.quesNo + '" >' + question.title + '</a></td>' +
                    '<td>' + question.writer + '</td>' +
                    '<td>' + question.regDate + '</td>' +
                    '<td>' + question.viewCnt + '</td>' +
                    '<td><span id="answer-button">ANSWER</span></td></tr>'
                    )

                    // // modal window, backdrop 숨기기
                    $('.modal').hide()
                    $('.blocker').hide()
                    //
                    // $('#detail-title').html(question.title)
                    // $('#detail-content').html(question.content)
                },
                error: function() {
                    alert("등록 실패했습니다.")
                }
            })

        })
    </script>
</body>
</html>
