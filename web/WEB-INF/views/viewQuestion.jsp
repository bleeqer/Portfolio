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

<div id="detail-question">
    TITLE : <div id="detail-title">${question.title}</div>
    <br>
    CONTENT : <div id="detail-content">${question.content}</div>
    <br>
    WRITER : <div id="detail-writer">${question.writer}</div>
    <br>
    <br>
    REGDATE : <div id="detail-writer">${question.regDate}</div>
    <br>
    <c:if test="${currentUserName == question.writer}">

        <span id="edit-button" data-id="${question.quesNo}">EDIT</span>
        <span id="delete-button" data-id="${question.quesNo}">DELETE</span>
    </c:if>
    <span id="replyAnswer-button">Reply</span>
    <br>
    <div id="answer-editor"></div>
</div>

    <span id="answer-button">ANSWER</span>
    <table id="answer-table">
        <c:forEach var="answer" items="${answers}">
            <tr class="answer-row" data-ans_no="${answer.ansNo}">
                <td>${answer.writer}</td>
                <td>${answer.content}</td>
                <td>${answer.regDate}</td>
                <td>${answer.viewCnt}</td>
                <td id="answerReply-button" data-ans_no="${answer.ansNo}">Reply</td>
            </tr>
        </c:forEach>


    </table>
<span id="more-button">More</span>


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

            $("#answer-editor").append(
                "<div id='answer'>" +
                    "<div id='user-profile'>사용자</div>" +
                    "<form id='answer-form' action='/answer/create'>" +
                        "<input id=answer-question-number value=${question.quesNo} type='hidden'>" +
                        "<input id=answer-writer value=${currentUserName} type='hidden'>" +
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

            initEditor()

            $('#answerSubmit-button').on('click', function () {

                // $('#answer-form').submit()
                tinymce.activeEditor.save()
                let quesNo = $('#answer-question-number').attr('value')
                let content = $('#content').val()
                let writer = $('#answer-writer').attr('value')

                let data = {quesNo: quesNo, content: content, writer: writer}

                $.ajax({
                    type: 'POST',

                    url: '/answer/create',

                    contentType: 'application/json; charset=utf-8',

                    data: JSON.stringify(data),

                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token)
                    },

                    dataType: 'json',

                    success: function(answer) {

                        tinymce.remove()
                        $('#answer-editor').empty()

                        // 답변 제출 성공 시 answer-button 재활성화
                        $("#answer-button").css("pointer-events", "auto")
                        $("#answer-button").css("color", "black")


                            $('#answer-table').prepend(
                                '<tr class="answer-row" data-ans_no="' + answer.ansNo + '">' +
                                '<td>' + answer.writer + '</td>' +
                                '<td>' + answer.content + '</td>' +
                                '<td>' + answer.regDate + '</td>' +
                                '<td>' + answer.viewCnt + '</td>' +
                                '<td id="answerReply-button" data-ans_no="' + answer.ansNo + '">Reply</td>' +
                                '</tr>'
                            )
                    },

                    error: function() {
                        alert("등록 실패했습니다.")
                    }
                })



            })

            $('#postCancel-button').on('click', function () {

                $('#answer-table').empty()
                tinymce.remove()

                // 답변 작성 취소 시 answer-button 재활성화
                $("#answer-button").css("pointer-events", "auto")
                $("#answer-button").css("color", "black")
            })

            $.getScript('/js/imageUpload.js')



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

    <script>
        $("#question-submit").on('click', function(e) {

            e.preventDefault()

            // tinymce 내용 textarea에 저장
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

                    // modal window 닫기
                    $('.close-modal').trigger('click')

                    $('#detail-title').html(question.title)
                    $('#detail-content').html(question.content)
                },

                error: function() {
                    alert("등록 실패했습니다.")
                }
            })
        })

        $('#more-button').on('click', function () {

            requestURL = '/answer/more'

            let lastAnswerNo = $('.answer-row').last().data('ans_no')

            let param = {ansNo: lastAnswerNo}

            $.ajax({
                type: 'POST',

                url: requestURL,

                contentType: 'application/json; charset=utf-8',

                data: JSON.stringify(param),

                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token)
                },

                dataType: 'json',

                success: function (answers) {

                    for (const answer of answers) {
                        $('#answer-table').append(
                            '<tr class="answer-row" data-ans_no="' + answer.ansNo + '">' +
                                '<td>' + answer.writer + '</td>' +
                                '<td>' + answer.content + '</td>' +
                                '<td>' + answer.regDate + '</td>' +
                                '<td>' + answer.viewCnt + '</td>' +
                                '<td id="answerReply-button" data-ans_no="' + answer.ansNo + '">Reply</td>' +
                            '</tr>'
                        )
                    }
                },
                error: function () {
                }

            })
        })
    </script>

</body>
</html>
