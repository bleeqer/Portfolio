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

    <%--  Bootstrap  --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <%-- Bootstrap Icon --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <style>

        .btn {
            font-size: 16px;
        }

        .bg-dark {
            background-color: #262626;
        }

        .bg-black {
            background-color: #181818;
        }

        .highlight-background:hover {
            opacity: 1;
        }

        /*highlight-transparent {*/
        /*    background-color: rgba(24, 24, 24, 0.8);*/
        /*}*/

        /*.highlight-transparent:hover {*/
        /*    opacity: 0.5;*/
        /*}*/

        html, body {
            padding: 0;
            margin: 0;
            color: #FFFFFF;
            font-size: 16px;
            background-color: #181818;
            /*-webkit-tap-highlight-color: rgba(24, 24, 24, 0.6);*/
        }

        .dropdown-menu {
            left: 50% !important;
            right: auto !important;
            transform: translateX(-50%) !important;
        }

        .icon_svg-stroke {
            stroke: rgb(177, 179, 182);
        }
    </style>

</head>
<body>
    <div class="container-fluid bg-dark">
        <nav class="navbar navbar-dark py-0 px-5">
            <a href="#" class="navbar-brand px-2">LOGO</a>
            <div class="dropdown ms-auto px-2">
                <span class="btn btn-secondary" id="user-dropdown" data-bs-toggle="dropdown">User</span>
                <ul class="dropdown-menu" aria-labelledby="book-dropdown">
                    <li><a class="dropdown-item" href="#">My Profile</a></li>
                    <li><a class="dropdown-item" href="#">My Questions</a></li>
                    <li><a class="dropdown-item" href="#">My Answers</a></li>
                    <div class="dropdown-divider"></div>
                    <li><a class="dropdown-item" href="#">Logout</a></li>
                </ul>
            </div>
            <div class="px-2">
            <a class="btn btn-danger rounded-pill my-3 py-1">Ask question</a>
            </div>
        </nav>
    </div>

    <div class="container-fluid p-0 m-0">
        <div class="mx-auto pt-3 px-2" style="max-width: 1100px;">

            <%--카테고리 리스트--%>
            <div class="mx-auto" style="max-width: 200px;">
            </div>

            <%--질문글 리스트--%>
            <div class="mx-auto" style="max-width: 700px;">
                <div>
                    <div>

                        <%--질문글--%>
                        <div class="rounded-1 mb-3 mx-1 border border-light shadow-sm">

                            <%--배경색--%>
                            <div class="bg-dark position-relative" style="height: 500px;">

                                <div class="px-3 pt-3 ">
                                    <div>

                                        <%--질문글--%>
                                        <div>
                                            <%--질문글 body--%>
                                            <div>

                                                <%--프로필--%>
                                                <div>
                                                    <div class="mb-3 d-flex">
                                                        <%--프로필 사진--%>
                                                        <div class="me-3">
                                                            <div>
                                                                <img src="" alt="" width="50" height="50">

                                                            </div>
                                                        </div>

                                                        <%--프로필 정보--%>
                                                        <div>
                                                            <%--이름--%>
                                                            <div class="">홍길동</div>

                                                            <%--상세--%>
                                                            <div class="">프로그래머</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <%--질문글 footer--%>
                                            <div>

                                            </div>
                                        </div>

                                        <div class="dropdown-divider"></div>

                                        <%--답변글--%>
                                        <div>
                                            <%--답변글 body--%>
                                            <div>

                                            </div>

                                            <%--답변글 footer--%>
                                            <div>

                                            </div>
                                        </div>

                                        <div class="dropdown-divider"></div>

                                        <%--Views, Upvotes 표시--%>
                                        <div class="mt-3 d-flex">
                                            <div>Views</div>
                                            <div>Upvotes</div>
                                        </div>

                                        <%--Upvote/Downvote, Comment, Share, Option 버튼--%>
                                        <div>

                                            <%--Container--%>
                                            <div class="d-flex">

                                                <%--div.Upvote/Downvote, Comment 버튼--%>
                                                <div class="d-flex">

                                                    <%--Upvote/Downvote 버튼--%>
                                                    <div class="me-1">

                                                        <%--Container--%>
                                                        <div class="d-flex">
                                                            <%--Upvote 버튼--%>
                                                            <div>Upvote</div>
                                                            <%--Divider--%>
                                                            <div> | </div>
                                                            <%--Downvote 버튼--%>
                                                            <div>Downvote</div>
                                                        </div>

                                                    </div>

                                                    <%--Comment 버튼--%>
                                                    <div class="">

                                                        <%--Container--%>
                                                        <div class="d-flex">
                                                            <div>CO</div>
<%--                                                            <span><svg width="24" height="24">CO</svg></span>--%>
                                                            <div>100</div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <%--Option 버튼--%>
                                                <div class="ms-auto">

                                                    <%--Container--%>
                                                    <div>
                                                        <span>
                                                            <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">

                                                                <path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z"
                                                                      class="icon_svg-stroke" stroke-width="1.5" stroke="#666" fill="none">
                                                                </path>

                                                            </svg>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="highlight-background" style="box-sizing: border-box;
                                     position: absolute;
                                     background-color: rgb(51, 51, 51);
                                     inset: 3px 0;
                                     z-index: 0;
                                     transition-property: opacity, transform;
                                     transition-duration: 180ms;
                                     transition-timing-function: ease-out;
                                     opacity: 0;">

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <h2>게시판</h2>
<%--    <sec:authorize access="!isAuthenticated()"><span id="login-button">Login</span></sec:authorize>--%>
<%--    <span id="login-button">Login</span>--%>
<%--    <span id="register-button">Register</span>--%>
<%--    <sec:authorize access="isAuthenticated()">Logout</sec:authorize>--%>
<%--    <sec:authorize access="hasRole('ADMIN')"><a href="#">Manage Users</a></sec:authorize>--%>
    <table id="question-table">
        <c:forEach var="category" items="${categories}">
            <a href="/topic/${category.categoryName}">${category.categoryName}</a>
        </c:forEach>
        <span id="ask-button">ASK QUESTION</span>
        <div id="question-list">
            <c:forEach var="question" items="${questions}">
                <tr class="question-row" data-ques_no="${question.quesNo}">
                    <td>${question.quesNo}</td>
                    <td><a href="/question/${question.quesNo}">${question.title}</a></td>
                    <td>${question.writer}</td>
                    <td>${question.regDate}</td>
                    <td>${question.viewCnt}</td>
                    <td><span id="answer-button">ANSWER</span></td>
                </tr>
            </c:forEach>
        </div><br/>
        <span id="more-button">More</span>

    </table>

    <%@ include file="/WEB-INF/views/modals/questionForm.jsp" %>

    <div id="topic" data-selected-topic="${selectedTopic}" style="display: none;"></div>
<%--    <%@ include file="/WEB-INF/views/modals/userLoginForm.jsp" %>--%>

    <form id="logout-form" action="/user/logout" method="POST" style="display: none;">
        <input id="input-csrf" type="text" name="_csrf">
    </form>
    <span id="logout-button"><a href="/user/logout">LOG OUT</a></span>
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

        const name = $("meta[name='_csrf_parameter']").attr("content")
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

            // tinymce 내용 textarea에 저장
            tinymce.activeEditor.save()

            // 작성자란에 현재 로그인 유저 아이디 입력
            $('#writer').val('<sec:authentication property="principal.username"/>')

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

                    $('#question-table').prepend(
                    '<tr class="question-row" data-ques_no="' + question.quesNo + '">' +
                    '<td>' + question.quesNo + '</td>' +
                    '<td><a href="/question/' + question.quesNo + '" >' + question.title + '</a></td>' +
                    '<td>' + question.writer + '</td>' +
                    '<td>' + question.regDate + '</td>' +
                    '<td>' + question.viewCnt + '</td>' +
                    '<td><span id="answer-button">ANSWER</span></td></tr>'
                    )
                },
                error: function() {
                    alert("등록 실패했습니다.")
                }
            })

        })
    </script>

    <script>
            $('#more-button').on('click', function () {
                // alert($(location).attr('pathname'))
                const topic = $('#topic').data('selected-topic')
                const requestURL = '/topic/more/' + topic
                const lastQuesNo = $('.question-row').last().data('ques_no')
                const param = {quesNo: lastQuesNo}

                $.ajax({
                    type: 'POST',

                    url: requestURL,

                    // contentType: 'application/json; charset=utf-8',
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',

                    data: param,

                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token)
                    },

                    dataType: 'json',

                    success: function (questions) {
                        for (const question of questions) {
                            $('#question-table').append(
                                '<tr class="question-row" data-ques_no="' + question.quesNo + '">' +
                                '<td>' + question.quesNo + '</td>' +
                                '<td><a href="/question/' + question.quesNo + '" >' + question.title + '</a></td>' +
                                '<td>' + question.writer + '</td>' +
                                '<td>' + question.regDate + '</td>' +
                                '<td>' + question.viewCnt + '</td>' +
                                '<td><span id="answer-button">ANSWER</span></td></tr>'
                            )
                        }
                    },

                    error: function () {
                    }

                })
            })

            $('#logout-button').on('click', function(e) {

                e.preventDefault()

                let logoutForm = $('#logout-form')

                logoutForm.find('#input-csrf').val(token)

                logoutForm.submit()

            })



    </script>
</body>
</html>
