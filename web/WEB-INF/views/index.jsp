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

    <sec:csrfMetaTags/>

    <%--  Bootstrap  --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <%-- Bootstrap Icon --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

    <%--CSS--%>
    <link rel="stylesheet" type="text/css" href="/static/css/utils.css">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="/static/css/popover.css">

    <style>
        input, textarea, button, a {
            color: rgb(213, 214, 214);
            background-color: transparent;
        }

        html, body {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
            font-size: 16px;
            border: none;
            background-color: #181818;
            color: rgb(213, 214, 214);
        }

        .icon_svg-stroke {
            stroke: rgb(177, 179, 182);
        }

        a {
            text-decoration: none;
            color: inherit;
        }

        a:hover {
            color: inherit;
        }

        select:focus {
            outline: none;
        }
    </style>

</head>
<body>

<div>

    <div style="height: 50px;"></div>

    <%--Navbar--%>
    <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

    <%--Search focus overlay--%>
    <div class="position-fixed bg-transparent-dark search-overlay"></div>


    <%--Main content--%>
    <div>
        <div class="container-fluid m-0" style="padding-top: 30px; padding-left: 24px; padding-right: 24px;">
            <div class="d-flex mx-auto position-relative" style="max-width: 1002px; min-width: 1002px;">

                <%--토픽 리스트--%>
                <div class="pb-5 position-fixed kuttDS"
                     style="height: calc(100vh - 80px); width: 166px; overflow-y:scroll !important;">
                    <div>
                        <%--개별 토픽 컨테이너--%>
                        <c:forEach var="category" items="${categories}">
                        <div class="d-flex mb-1 rounded-1 bg-hover-whiten-light">
                            <a class="topic-item text-decoration-none" href="/topic/${category.categoryName}">
                                <div class="p-2 d-flex align-items-center" style="height: auto;">

                                        <%--토픽 아이콘--%>
                                    <div class="me-2">
                                        <div class="d-flex rounded-1 overflow-hidden">
                                            <img class="m-0" src="${category.categoryPhoto}" alt="IMG" width="18"
                                                 height="18">
                                        </div>
                                    </div>

                                        <%--카테고리 이름--%>
                                    <div class="d-flex">
                                        <div class="d-flex font-size-1">
                                            <div class="d-flex align-items-center justify-content-start">${category.categoryName}</div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </div>
                        </c:forEach>
                    </div>
                </div>

                <%--질문글 리스트--%>
                <div style="width: 572px; margin-left: 172px;">
                    <div>
                        <div>

                            <c:forEach var="answerPair" items="${answerPairs}">

                                <%--질문글--%>
                                <div class="question-container mb-3 mx-1" data-ques-no="${answerPair.quesNo}">

                                        <%--배경색--%>
                                    <div class="rounded-3 shadow-sm border border-color-dark bg-dark position-relative bg-hover-whiten">
                                        <div class="px-4 pt-3" style="height: 100%;">
                                            <div>

                                                    <%--질문글--%>
                                                <div>

                                                        <%--질문글 body--%>
                                                    <div>
                                                        <div>

                                                                <%--답변자 프로필--%>
                                                            <div class="mb-2 d-flex">

                                                                    <%--프로필 사진--%>
                                                                <div class="me-3 position-relative">
                                                                    <div>
                                                                        <img class="rounded-7 border border-color-whiten"
                                                                             src="/static/img/flower.jpeg" alt=""
                                                                             width="50" height="50">
                                                                    </div>
                                                                    <div class="bg-hover-darken position-absolute"
                                                                         style="inset: 0;">
                                                                    </div>
                                                                </div>

                                                                    <%--프로필 정보--%>
                                                                <div class="d-flex flex-column">

                                                                        <%--이름--%>
                                                                    <div class="my-auto font-size-1"><a
                                                                            class="text-decoration-none link-default-color hover-underline h6"
                                                                            href="/user/profile/${answerPair.answerUserNo}"><strong>${answerPair.answerUserName}</strong></a>
                                                                    </div>

                                                                        <%--상세--%>
                                                                    <div class="my-auto color-gray font-size-1">${answerPair.answerUserOccupation}<span> · </span>${answerPair.answerRegDate}
                                                                    </div>

                                                                </div>
                                                            </div>
                                                                <%--질문글 내용--%>
                                                            <div>
                                                                <div>
                                                                    <a class="text-decoration-none link-default-color hover-underline"
                                                                       href="#"><strong>${answerPair.question}</strong></a>
                                                                </div>
                                                            </div>

                                                                <%--답변글 내용--%>
                                                            <div class="position-relative">
                                                                <div class="fade-post">
                                                                    <div class="" style="line-height: 1.5rem;">
                                                                            ${answerPair.answer}
                                                                    </div>
                                                                    <div class="overflow-fade hidden"></div>
                                                                </div>
                                                                <span class="readMore-button btn btn-primary rounded-pill hidden">More</span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                        <%--질문글 footer--%>
                                                    <div>

                                                    </div>
                                                </div>


                                                    <%--Upvote/Downvote, Comment, Share, Option 버튼--%>
                                                <div class="pe-1">

                                                        <%--Container--%>
                                                    <div class="d-flex justify-content-center align-items-center my-1"
                                                         style="height: 46px;">

                                                            <%--div.Upvote/Downvote, Comment 버튼--%>
                                                        <div class="d-flex justify-content-center align-items-center">

                                                                <%--Upvote/Downvote 버튼--%>
                                                            <div class="me-1 d-flex justify-content-center align-items-center">

                                                                    <%--Container--%>
                                                                <div class="d-flex rounded-pill" role="button"
                                                                     style="background-color: rgba(255, 255, 255, 0.05); height: 30px;">
                                                                        <%--Like 버튼--%>
                                                                    <div class="like-button d-flex justify-content-center align-items-center bg-hover-whiten px-2 rounded-left-top-pill rounded-left-bottom-pill">
                                                                        <div class="d-flex">
                                                                            <div>
                                                                                <span class="d-inline-block d-flex align-items-center justify-content-center">
                                                                                    <svg width="20" height="20"
                                                                                         viewBox="0 0 24 24"
                                                                                         xmlns="http://www.w3.org/2000/svg">
                                                                                        <path d="M12 4 3 15h6v5h6v-5h6z" stroke-width="1.5" stroke="rgb(72, 148, 253)" fill="none" stroke-linejoin="round"></path>
                                                                                    </svg>
                                                                                </span>
                                                                            </div>
                                                                            <div class="d-flex align-items-center justify-content-center"
                                                                                 style="font-size: 13px; margin-left: 2px;">
                                                                                <c:if test="${answerPair.answerLikeCnt > 0}">
                                                                                    ${answerPair.answerLikeCnt}
                                                                                </c:if>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                        <%--Unlike 버튼--%>
                                                                    <div class="unlike-button d-flex justify-content-center align-items-center bg-hover-whiten px-2 rounded-right-top-pill rounded-right-bottom-pill">
                                                                        <div class="d-flex">
                                                                            <div>
                                                                                <span class="d-inline-block d-flex align-items-center justify-content-center">
                                                                                    <svg width="20" height="20" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                                                        <path d="m12 20 9-11h-6V4H9v5H3z" class="icon_svg-stroke icon_svg-fill" stroke="#666" fill="none"
                                                                                              stroke-width="1.5" stroke-linejoin="round"></path>
                                                                                    </svg>
                                                                                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                                <%--Comment 버튼--%>
                                                            <div role="button">

                                                                    <%--Container--%>
                                                                <div>

                                                                        <%--Comment button--%>
                                                                    <div class="d-flex rounded-pill px-2 bg-hover-whiten">

                                                                            <%--Comment icon--%>
                                                                        <span class="d-inline-block d-flex align-items-center justify-content-center"
                                                                              style="height: 30px;">
                                                                            <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                                                <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z"
                                                                                      class="icon_svg-stroke icon_svg-fill" stroke="#666" stroke-width="1.5" fill="none"></path>
                                                                            </svg>
                                                                        </span>

                                                                            <%--Comment count--%>
                                                                        <div class="d-flex align-items-center justify-content-center color-gray">
                                                                            100
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                            <%--Options--%>
                                                        <div class="ms-auto d-flex">

                                                                <%--Share button--%>
                                                            <div>
                                                                <div>
                                                                    <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center"
                                                                          style="width: 38px; height: 38px;">
                                                                        <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                                            <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z"
                                                                                  class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>
                                                                        </svg>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                                <%--Three dots--%>
                                                            <c:if test="${pageContext.request.userPrincipal.name == answerPair.answerUserEmail}">
                                                                <div>
                                                                    <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center"
                                                                          style="width: 38px; height: 38px;">
                                                                        <svg class="d-block" width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z"
                                                                                                                                                                                 class="icon_svg-stroke" stroke-width="1.5" stroke="#666" fill="none"></path>
                                                                        </svg>
                                                                    </span>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <%--주간 조회, 좋아요 베스트--%>
                <div class="position-fixed" style="width: 238px; margin-left: 758px;">

                    <%--주간 조회수 베스트--%>
                    <div class="bg-dark rounded-3 mb-2">
                        <div class="px-4 py-2 border-bottom-gray">
                            <div class="text-center">
                                <b>Most viewed</b>
                            </div>
                        </div>

                        <div class="best-title px-3 bg-hover-whiten-light py-1 dark border-bottom-gray"
                             style="transition: height 250ms; height: 32px; overflow: hidden;">
                            <div>
                                <div>
                                    <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 206px;">
                                        hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <%--주간 좋아요 베스트--%>
                    <div class="bg-dark rounded-3">
                        <div class="px-4 py-2 border-bottom-gray">
                            <div class="text-center">
                                <b>Most liked</b>
                            </div>
                        </div>

                        <div class="best-title px-3 bg-hover-whiten-light py-1 dark border-bottom-gray"
                             style="transition: height 250ms; height: 32px; overflow: hidden;">
                            <div>
                                <div>
                                    <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 206px;">
                                        hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/views/modals/question.jsp" %>


    <script src="/static/js/utils.js"></script>
    <script src="/static/js/questions.js"></script>
    <script src="/static/js/getMore.js"></script>
    <script src="/static/js/main.js"></script>

</body>
</html>
