<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 4/21/22
  Time: 11:02 PM
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
        <div class="d-flex justify-content-center mx-auto"
             style="width: 1002px; padding-top: 30px; padding-left: 24px; padding-right: 24px;">

            <%--Main content container--%>
            <div style="width: 572px;">

                <%--Profile--%>
                <div>
                    <div class="d-flex">
                        <%--Profile picture--%>
                        <div class="d-inline-block" style="margin-right: 24px;">
                            <div class="rounded-circle overflow-hidden d-inline-block">
                                <img src="/static/img/user.png" alt="" width="120" height="120">
                            </div>
                        </div>
                        <%--Profile description--%>
                        <div class="d-flex flex-column justify-content-center">

                            <%--User name--%>
                            <div>
                                <div>
                                    <h1 class="m-0">${user.name}</h1>
                                </div>
                            </div>

                            <%--User credential--%>
                            <div class="mt-2 color-dark-gray">
                                <div>
                                    ${user.occupation}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--Answers and etc--%>
                <div class="mt-2">
                    <div>
                        <%--User profile tabs--%>
                        <div class="border-bottom-gray">
                            <div class="d-flex color-dark-gray">
                                <div class="py-3 px-2 color-dark-gray bg-hover-whiten-light position-relative color-red"
                                     style="font-size: 13px;">
                                    <div><b>Profile</b></div>
                                    <div class="rounded-left-top-pill rounded-right-top-pill bg-red position-absolute"
                                         style="height: 3px; width: 100%; left: 50%; bottom: 0; transform: translateX(-50%);"></div>
                                </div>
                                <div class="py-3 px-2 color-dark-gray bg-hover-whiten-light position-relative"
                                     style="font-size: 13px;">
                                    <a href="/user/profile/${user.userNo}/answers">
                                        <div><b>Answers</b></div>
                                    </a>
                                </div>
                                <div class="py-3 px-2 color-dark-gray bg-hover-whiten-light position-relative"
                                     style="font-size: 13px;">
                                    <a href="/user/profile/${user.userNo}/questions">
                                        <div><b>Questions</b></div>
                                    </a>
                                </div>
                            </div>
                        </div>

                        <%--Selected contents--%>
                        <div>

                            <%--Content name--%>
                            <div class="py-2 border-bottom-gray">
                                <div class="d-flex align-items-center">
                                    <b>0 Questions</b>
                                </div>
                            </div>

                            <%--Content view for none--%>
                            <div class="py-3 px-4 d-flex justify-content-center hidden">
                                <div class="py-4">
                                    <div class="d-flex justify-content-center">
                                        <img class="mb-1" src="/static/img/mailbox.png" alt="" width="100">
                                    </div>

                                    <div class="link-default-color">
                                        <div>작성한 답변이 없습니다</div>
                                        <div>작성한 질문이 없습니다</div>
                                    </div>
                                </div>
                            </div>

                            <%--Content view for answers or questions--%>
                            <div class="pt-3 border-bottom-gray">
                                <div>

                                    <%--answers--%>
                                    <c:if test="${answerPairs != null}">
                                        <c:forEach var="answerPair" items="${answerPairs}">
                                            <div class="border-bottom-gray">

                                                <div>

                                                        <%--Profile and content--%>
                                                    <div>

                                                            <%--질문자 프로필--%>
                                                        <div class="mb-2 d-flex">

                                                                <%--프로필 사진--%>
                                                            <div class="me-1 position-relative d-flex justify-content-center align-items-center">
                                                                <div>
                                                                    <img class="rounded-circle border border-color-whiten"
                                                                         src="${answerPair.answerUserPhoto}" alt=""
                                                                         width="36" height="36">
                                                                </div>
                                                                <div class="bg-hover-darken position-absolute"
                                                                     style="inset: 0;">
                                                                </div>
                                                            </div>

                                                                <%--프로필 정보--%>
                                                            <div class="d-flex flex-column">

                                                                    <%--이름--%>
                                                                <div class="my-auto"><a
                                                                        class="text-decoration-none link-default-color hover-underline h6"
                                                                        href=""
                                                                        style="font-size: 13px;"><strong>${answerPair.answerUserName}</strong></a>
                                                                </div>

                                                                    <%--상세--%>
                                                                <div class="my-auto color-gray"
                                                                     style="font-size: 13px;">${answerPair.answerUserOccupation}<span> · </span>${answerPair.answerRegDate}
                                                                </div>

                                                            </div>
                                                        </div>
                                                            <%--질문글 제목--%>
                                                        <div>
                                                            <div>
                                                                <a class="text-decoration-none link-default-color hover-underline"
                                                                   href="#"
                                                                   style="font-size: 16px;"><b>${answerPair.question}</b></a>
                                                            </div>
                                                        </div>

                                                            <%--질문글 내용--%>
                                                        <div class="position-relative">
                                                            <div class="fade-post" style="max-height: 100px;">
                                                                <div class=""
                                                                     style="font-size: 15px; line-height: 1.5rem;">
                                                                        ${answerPair.answer}
                                                                    <img src="/static/img/flower.jpeg" alt=""
                                                                         width="100%">
                                                                </div>
                                                                <div class="overflow-fade"
                                                                     style="background: linear-gradient(to bottom, transparent 60%, rgb(24, 24, 24) 100%) !important;"></div>
                                                            </div>
                                                            <span class="readMore-button btn btn-primary rounded-pill hidden">More</span>
                                                        </div>
                                                    </div>

                                                        <%--Footer--%>
                                                    <div>

                                                            <%--Container--%>
                                                        <div class="d-flex justify-content-center align-items-center py-1"
                                                             style="height: 46px;">

                                                                <%--Upvote/Downvote, Comment 버튼--%>
                                                            <div class="d-flex justify-content-center align-items-center">

                                                                    <%--Upvote/Downvote 버튼--%>
                                                                <div class="me-1 d-flex justify-content-center align-items-center">

                                                                        <%--Container--%>
                                                                    <div class="d-flex rounded-pill" role="button"
                                                                         style="background-color: rgba(255, 255, 255, 0.05);">
                                                                            <%--Upvote 버튼--%>
                                                                        <div class="d-flex justify-content-center align-items-center bg-hover-whiten-light rounded-left-top-pill rounded-left-bottom-pill"
                                                                             style="height: 30px; padding: 0px 10px;">
                                                                            <div class="d-flex">
                                                                                <div>
                                                                                    <span class="d-inline-block d-flex align-items-center justify-content-center">
                                                                                        <svg width="20" height="20"
                                                                                             viewBox="0 0 24 24"
                                                                                             xmlns="http://www.w3.org/2000/svg">
                                                                                            <path d="M12 4 3 15h6v5h6v-5h6z"
                                                                                                  stroke-width="1.5"
                                                                                                  stroke="rgb(72, 148, 253)"
                                                                                                  fill="none"
                                                                                                  stroke-linejoin="round"></path>
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
                                                                            <%--Divider--%>
                                                                            <%--Downvote 버튼--%>
                                                                        <div class=" d-flex justify-content-center align-items-center bg-hover-whiten-light px-2 rounded-right-top-pill rounded-right-bottom-pill"
                                                                             style="height: 30px; padding: 0px 10px;">
                                                                            <div>
                <span class="d-inline-block d-flex align-items-center justify-content-center">
      <svg width="20" height="20" viewBox="0 0 24 24"
           xmlns="http://www.w3.org/2000/svg">
        <path d="m12 20 9-11h-6V4H9v5H3z"
              class="icon_svg-stroke icon_svg-fill"
              stroke="#666" fill="none"
              stroke-width="1.5"
              stroke-linejoin="round"></path>
      </svg>
                </span>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </div>

                                                                    <%--Comment 버튼--%>
                                                                <div role="button">

                                                                        <%--Container--%>
                                                                    <div>

                                                                            <%--Comment button--%>
                                                                        <div class="d-flex rounded-pill px-2 bg-hover-whiten-light"
                                                                             style="padding: 0 10px;">

                                                                                <%--Comment icon--%>
                                                                            <span class="d-inline-block d-flex align-items-center justify-content-center"
                                                                                  style="height: 30px;">
                <svg width="20" height="20" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
      <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z"
            class="icon_svg-stroke icon_svg-fill"
            stroke="#666" stroke-width="1.5"
            fill="none"></path>
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
                <svg width="24" height="24" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
            <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z"
                  class="icon_svg-stroke" stroke="#666"
                  fill="none" stroke-width="1.5"
                  stroke-linejoin="round"></path>
                </svg>
              </span>
                                                                    </div>
                                                                </div>

                                                                    <%--Three dots--%>
                                                                <div>
            <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center"
                  style="width: 38px; height: 38px;">
              <svg class="d-block" width="24" height="24"
                   viewBox="0 0 24 24"
                   xmlns="http://www.w3.org/2000/svg">
                <path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z"
                      class="icon_svg-stroke" stroke-width="1.5"
                      stroke="#666" fill="none">
                </path>
              </svg>
            </span>
                                                                </div>
                                                            </div>


                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <%--questions--%>
                                    <c:if test="${questions != null}">
                                        <c:forEach var="question" items="${questions}">
                                            <div>

                                                <div>

                                                        <%--Content--%>
                                                    <div class="unanswered-question border-bottom-gray">
                                                        <div>
                                                            <div>
                                                                    <%--title and close--%>
                                                                <div>
                                                                    <div class="d-flex justify-content-between position-relative">
                                                                            <%--Question title--%>
                                                                        <div class="pe-2">
                                                                            <a class="hover-underline"
                                                                               href="/question/${question.quesNo}"><b>${question.question}</b></a>
                                                                        </div>
                                                                            <%--Question close--%>
                                                                            <%--              <div class="position-absolute" style="top: -12px; right: -12px;">--%>
                                                                            <%--      <div>--%>
                                                                            <%--          <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">--%>
                                                                            <%--            <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                                                                            <%--              <path d="m16.243 7.757-8.486 8.486m8.486 0L7.757 7.757" class="icon_svg-stroke" fill="none" fill-rule="evenodd" stroke-linecap="round" stroke="#666" stroke-width="1.5"></path>--%>
                                                                            <%--            </svg>--%>
                                                                            <%--          </span>--%>
                                                                            <%--      </div>--%>
                                                                            <%--              </div>--%>
                                                                    </div>
                                                                </div>

                                                                    <%--Posted date--%>
                                                                <div class="mt-2 color-gray" style="font-size: 13px;">
                                                                    <div>
                                                                        <div>
                                                                                ${question.regDate}
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                    <%--Question footer--%>
                                                                <div class="mt-1">
                                                                    <div style="padding-top: 1px; padding-bottom: 1px;">
                                                                        <div class="d-flex justify-content-between align-items-center">
                                                                            <div class="d-flex">
                                                                                    <%--Answer button--%>
                                                                                <button class="px-2 rounded-pill bg-hover-whiten-light"
                                                                                        style="margin-left: -0.5rem; height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;"
                                                                                        data-bs-toggle="modal"
                                                                                        data-bs-target="#answer-modal">
                                                                                    <div class="d-flex justify-content-center align-items-center">
                                                                                            <%--Answer icon--%>
                                                                                        <div>
                                                                                            <svg width="24" height="24"
                                                                                                 viewBox="0 0 24 24"
                                                                                                 xmlns="http://www.w3.org/2000/svg">
                                                                                                <g stroke-width="1.5"
                                                                                                   fill="none"
                                                                                                   fill-rule="evenodd">
                                                                                                    <path d="M18.571 5.429h0a2 2 0 0 1 0 2.828l-9.9 9.9-4.24 1.416 1.412-4.245 9.9-9.9h0a2 2 0 0 1 2.828 0Z"
                                                                                                          stroke="rgb(72, 148, 253)"
                                                                                                          stroke-linecap="round"
                                                                                                          stroke-linejoin="round"></path>
                                                                                                    <path fill="#666"
                                                                                                          d="m4.429 19.571 2.652-.884-1.768-1.768z"></path>
                                                                                                    <path d="M14.5 19.5h5v-5m-10-10h-5v5"
                                                                                                          stroke="rgb(72, 148, 253)"
                                                                                                          stroke-linecap="round"
                                                                                                          stroke-linejoin="round"></path>
                                                                                                </g>
                                                                                            </svg>
                                                                                        </div>
                                                                                            <%--Answer text--%>
                                                                                        <div class="color-gray ms-1"
                                                                                             style="font-size: 14px;">
                                                                                            <div>Answer</div>
                                                                                        </div>
                                                                                    </div>
                                                                                </button>

                                                                                    <%--Comment 버튼--%>
                                                                                    <%--          <div role="button">--%>

                                                                                    <%--              &lt;%&ndash;Container&ndash;%&gt;--%>
                                                                                    <%--              <div class="d-inline">--%>

                                                                                    <%--            &lt;%&ndash;Comment button&ndash;%&gt;--%>
                                                                                    <%--            <div class="d-flex rounded-pill px-2 bg-hover-whiten-light">--%>

                                                                                    <%--                &lt;%&ndash;Comment icon&ndash;%&gt;--%>
                                                                                    <%--                <span class="d-inline-block d-flex align-items-center justify-content-center" style="height: 38px;">--%>
                                                                                    <%--              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                                                                                    <%--            <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z" class="icon_svg-stroke icon_svg-fill" stroke="#666" stroke-width="1.5" fill="none"></path>--%>
                                                                                    <%--              </svg>--%>
                                                                                    <%--                </span>--%>

                                                                                    <%--                &lt;%&ndash;Comment count&ndash;%&gt;--%>
                                                                                    <%--                <div class="d-flex align-items-center justify-content-center">--%>
                                                                                    <%--            100--%>
                                                                                    <%--                </div>--%>
                                                                                    <%--            </div>--%>
                                                                                    <%--              </div>--%>
                                                                                    <%--          </div>--%>

                                                                                    <%--Pass button--%>
                                                                                    <%--          <button class="px-2 rounded-pill bg-hover-whiten-light" style="height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;">--%>
                                                                                    <%--              <div class="d-flex justify-content-center align-items-center">--%>
                                                                                    <%--            &lt;%&ndash;Answer icon&ndash;%&gt;--%>
                                                                                    <%--            <div>--%>
                                                                                    <%--                <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                                                                                    <%--            <g fill="none" fill-rule="evenodd">--%>
                                                                                    <%--                <path d="m11.828 9.314 3.9-3.9a2 2 0 1 1 2.828 2.829l-3.9 3.9m-3.535 3.535-2.464 2.464-4.241 1.416 1.412-4.244 2.465-2.465" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="square" stroke-linejoin="round"></path>--%>
                                                                                    <%--                <path class="icon_svg-fill_as_stroke" fill="#666" d="m4.414 19.556 2.652-.884-1.768-1.767z"></path>--%>
                                                                                    <%--                <path d="M4.636 5.636 18.5 19.5" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="round"></path>--%>
                                                                                    <%--            </g>--%>
                                                                                    <%--                </svg>--%>
                                                                                    <%--            </div>--%>
                                                                                    <%--            &lt;%&ndash;Answer text&ndash;%&gt;--%>
                                                                                    <%--            <div class="color-gray ms-1" style="font-size: 14px;">--%>
                                                                                    <%--                <div>Pass</div>--%>
                                                                                    <%--            </div>--%>
                                                                                    <%--              </div>--%>
                                                                                    <%--          </button>--%>
                                                                            </div>

                                                                                <%--right side of footer--%>
                                                                            <div class="d-flex">

                                                                                    <%--Share button--%>
                                                                                <div>
                                                                                    <div>
            <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center"
                  style="width: 38px; height: 38px;">
              <svg width="24" height="24"
                   viewBox="0 0 24 24"
                   xmlns="http://www.w3.org/2000/svg">
                <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z"
                      class="icon_svg-stroke"
                      stroke="#666" fill="none"
                      stroke-width="1.5"
                      stroke-linejoin="round"></path>
              </svg>
            </span>
                                                                                    </div>
                                                                                </div>

                                                                                    <%--Three dots--%>
                                                                                <div>
              <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center"
                    style="width: 38px; height: 38px;">
                <svg class="d-block" width="24"
                     height="24" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
            <path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z"
                  class="icon_svg-stroke"
                  stroke-width="1.5"
                  stroke="#666" fill="none">
            </path>
                </svg>
              </span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>

                                                    <%--      &lt;%&ndash;Question&ndash;%&gt;--%>
                                                    <%--      <div>--%>

                                                    <%--      </div>--%>

                                                    <%--      &lt;%&ndash;Answer&ndash;%&gt;--%>
                                                    <%--      <div>--%>

                                                    <%--      </div>--%>

                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>

                            <%--Content view for questions--%>
                            <%--        <div class="pt-3 border-bottom-gray">--%>
                            <%--            <div>--%>

                            <%--            &lt;%&ndash;Content&ndash;%&gt;--%>
                            <%--            <div>--%>

                            <%--      <div>--%>

                            <%--          &lt;%&ndash;Content&ndash;%&gt;--%>
                            <%--          <div class="unanswered-question border-bottom-gray">--%>
                            <%--          <div>--%>
                            <%--          <div>--%>
                            <%--        &lt;%&ndash;title and close&ndash;%&gt;--%>
                            <%--        <div>--%>
                            <%--            <div class="d-flex justify-content-between position-relative">--%>
                            <%--                &lt;%&ndash;Question title&ndash;%&gt;--%>
                            <%--                <div class="pe-2">--%>
                            <%--              <a class="hover-underline" href=""><b>Is this the content of the questions?</b></a>--%>
                            <%--                </div>--%>
                            <%--                &lt;%&ndash;Question close&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              <div class="position-absolute" style="top: -12px; right: -12px;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;      <div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              <path d="m16.243 7.757-8.486 8.486m8.486 0L7.757 7.757" class="icon_svg-stroke" fill="none" fill-rule="evenodd" stroke-linecap="round" stroke="#666" stroke-width="1.5"></path>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            </svg>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          </span>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;      </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              </div>&ndash;%&gt;--%>
                            <%--            </div>--%>
                            <%--        </div>--%>

                            <%--        &lt;%&ndash;Posted date&ndash;%&gt;--%>
                            <%--        <div class="mt-2 color-gray" style="font-size: 13px;">--%>
                            <%--            <div>--%>
                            <%--                <div>--%>
                            <%--              asked 00 ago--%>
                            <%--                </div>--%>
                            <%--            </div>--%>
                            <%--        </div>--%>

                            <%--        &lt;%&ndash;Question footer&ndash;%&gt;--%>
                            <%--        <div class="mt-1">--%>
                            <%--            <div style="padding-top: 1px; padding-bottom: 1px;">--%>
                            <%--                <div class="d-flex justify-content-between align-items-center">--%>
                            <%--              <div class="d-flex">--%>
                            <%--      &lt;%&ndash;Answer button&ndash;%&gt;--%>
                            <%--      <button class="px-2 rounded-pill bg-hover-whiten-light" style="margin-left: -0.5rem; height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;" data-bs-toggle="modal" data-bs-target="#answer-modal">--%>
                            <%--          <div class="d-flex justify-content-center align-items-center">--%>
                            <%--              &lt;%&ndash;Answer icon&ndash;%&gt;--%>
                            <%--              <div>--%>
                            <%--            <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                            <%--                <g stroke-width="1.5" fill="none" fill-rule="evenodd">--%>
                            <%--            <path d="M18.571 5.429h0a2 2 0 0 1 0 2.828l-9.9 9.9-4.24 1.416 1.412-4.245 9.9-9.9h0a2 2 0 0 1 2.828 0Z" stroke="rgb(72, 148, 253)" stroke-linecap="round" stroke-linejoin="round"></path>--%>
                            <%--            <path fill="#666" d="m4.429 19.571 2.652-.884-1.768-1.768z"></path>--%>
                            <%--            <path d="M14.5 19.5h5v-5m-10-10h-5v5"  stroke="rgb(72, 148, 253)" stroke-linecap="round" stroke-linejoin="round"></path>--%>
                            <%--                </g>--%>
                            <%--            </svg>--%>
                            <%--              </div>--%>
                            <%--              &lt;%&ndash;Answer text&ndash;%&gt;--%>
                            <%--              <div class="color-gray ms-1" style="font-size: 14px;">--%>
                            <%--            <div>Answer</div>--%>
                            <%--              </div>--%>
                            <%--          </div>--%>
                            <%--      </button>--%>

                            <%--      &lt;%&ndash;Comment 버튼&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          <div role="button">&ndash;%&gt;--%>

                            <%--&lt;%&ndash;              &lt;%&ndash;Container&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              <div class="d-inline">&ndash;%&gt;--%>

                            <%--&lt;%&ndash;            &lt;%&ndash;Comment button&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <div class="d-flex rounded-pill px-2 bg-hover-whiten-light">&ndash;%&gt;--%>

                            <%--&lt;%&ndash;                &lt;%&ndash;Comment icon&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <span class="d-inline-block d-flex align-items-center justify-content-center" style="height: 38px;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z" class="icon_svg-stroke icon_svg-fill" stroke="#666" stroke-width="1.5" fill="none"></path>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              </svg>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                </span>&ndash;%&gt;--%>

                            <%--&lt;%&ndash;                &lt;%&ndash;Comment count&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <div class="d-flex align-items-center justify-content-center">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            100&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          </div>&ndash;%&gt;--%>

                            <%--      &lt;%&ndash;Pass button&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          <button class="px-2 rounded-pill bg-hover-whiten-light" style="height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              <div class="d-flex justify-content-center align-items-center">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            &lt;%&ndash;Answer icon&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <g fill="none" fill-rule="evenodd">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <path d="m11.828 9.314 3.9-3.9a2 2 0 1 1 2.828 2.829l-3.9 3.9m-3.535 3.535-2.464 2.464-4.241 1.416 1.412-4.244 2.465-2.465" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="square" stroke-linejoin="round"></path>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <path class="icon_svg-fill_as_stroke" fill="#666" d="m4.414 19.556 2.652-.884-1.768-1.767z"></path>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <path d="M4.636 5.636 18.5 19.5" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="round"></path>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            </g>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                </svg>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            &lt;%&ndash;Answer text&ndash;%&gt;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            <div class="color-gray ms-1" style="font-size: 14px;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;                <div>Pass</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;              </div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;          </button>&ndash;%&gt;--%>
                            <%--              </div>--%>

                            <%--              &lt;%&ndash;right side of footer&ndash;%&gt;--%>
                            <%--              <div class="d-flex">--%>

                            <%--      &lt;%&ndash;Share button&ndash;%&gt;--%>
                            <%--      <div>--%>
                            <%--          <div>--%>
                            <%--            <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">--%>
                            <%--              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                            <%--                <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z" class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>--%>
                            <%--              </svg>--%>
                            <%--            </span>--%>
                            <%--          </div>--%>
                            <%--      </div>--%>

                            <%--          &lt;%&ndash;Three dots&ndash;%&gt;--%>
                            <%--          <div>--%>
                            <%--              <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">--%>
                            <%--                <svg class="d-block" width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
                            <%--            <path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z"--%>
                            <%--              class="icon_svg-stroke" stroke-width="1.5" stroke="#666" fill="none">--%>
                            <%--            </path>--%>
                            <%--                </svg>--%>
                            <%--              </span>--%>
                            <%--          </div>--%>
                            <%--              </div>--%>
                            <%--                </div>--%>
                            <%--            </div>--%>
                            <%--        </div>--%>
                            <%--          </div>--%>
                            <%--          </div>--%>
                            <%--          </div>--%>

                            <%--      </div>--%>

                            <%--      &lt;%&ndash;      &lt;%&ndash;Question&ndash;%&gt;&ndash;%&gt;--%>
                            <%--      &lt;%&ndash;      <div>&ndash;%&gt;--%>

                            <%--      &lt;%&ndash;      </div>&ndash;%&gt;--%>

                            <%--      &lt;%&ndash;      &lt;%&ndash;Answer&ndash;%&gt;&ndash;%&gt;--%>
                            <%--      &lt;%&ndash;      <div>&ndash;%&gt;--%>

                            <%--      &lt;%&ndash;      </div>&ndash;%&gt;--%>

                            <%--            </div>--%>

                            <%--            </div>--%>
                            <%--        </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<%--    <div class="container-fluid m-0" style="padding-top: 50px;">--%>
<%--        <div class="d-flex mx-auto pt-3 px-2" style="max-width: 1002px;">--%>

<%--        </div>--%>
<%--    </div>--%>
<script src="/static/js/utils.js"></script>
<script src="/static/js/questions.js"></script>
</body>
</html>
