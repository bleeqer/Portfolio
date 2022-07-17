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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Title</title>

  <%--Jquery--%>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <sec:csrfMetaTags/>

  <script>
    const token = $("meta[name='_csrf']").attr("content")
    const header = $("meta[name='_csrf_header']").attr("content")
  </script>

  <%--Tinymce--%>
  <script src="https://cdn.tiny.cloud/1/gus813frvbl3fe4mc223cqoe7nmtzbc5kbk3xc2dzvawxe52/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

  <%--  Bootstrap  --%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

  <%-- Bootstrap Icon --%>
<%--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">--%>

  <%--CSS--%>
  <link rel="stylesheet" type="text/css" href="/static/css/utils.css">
  <link rel="stylesheet" type="text/css" href="/static/css/main.css">
  <link rel="stylesheet" type="text/css" href="/static/css/popover.css">
  <link rel="stylesheet" type="text/css" href="/static/css/answer.css">

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

    select:focus {
      outline: none;
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
        <div class="d-flex mx-auto" style="max-width: 1002px;">

          <%--토픽 리스트--%>
          <div class="pb-5 position-fixed kuttDS"
               style="height: calc(100vh - 80px); width: 166px; overflow-y:scroll !important;">
            <div>
              <%--개별 토픽 컨테이너--%>
              <c:forEach var="category" items="${categories}">
                <div class="category-item d-flex mb-1 rounded-1 <c:if test="${selectedCategory == category.categoryCode}">selected</c:if> bg-hover-whiten-light" data-category-code="${category.categoryCode}">
                  <a class="topic-item text-decoration-none" href="/questions/category/${category.categoryCode}">
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
          <div id="content-container" style="width: 572px; margin-left:172px;">
            <div>
              <div>


                <%--질문글--%>
                <div class="mb-3 mx-1">

                  <%--배경색--%>
                  <div class="rounded-3 shadow-sm border border-color-dark bg-dark position-relative">

                    <%--Questions for you--%>
                    <div class="px-3 py-2 border-bottom-gray">
                      <div class="d-flex justify-content-between">
                        <div class="d-flex align-items-center">
                          <div class="d-inline-flex rounded-2 overflow-hidden">
                            <div class="d-flex justify-content-center align-items-center bg-red" style="width: 24px; height: 24px;">
                            <span class="d-inline-flex justify-content-center align-items-center" style="width: 16px; height: 16px; line-height: 16px;">
                              <svg width="16" height="16" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path d="m12 16.618-4.944 2.599L8 13.71 4 9.812l5.528-.803L12 4l2.472 5.01L20 9.811l-4 3.9.944 5.505z" stroke="rgb(255, 255, 255)" stroke-width="2.1" fill="rgb(255, 255, 255)" stroke-linejoin="round"></path>
                              </svg>
                            </span>
                            </div>
                          </div>
                          <div class="d-inline-flex ms-2">
                            <div class="color-gray" style="font-size: 13px;">
                              Questions for you
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div id="question-list" style="height: 100%;">
                      <div>

                      <c:forEach var="question" items="${questions}">

                        <%--질문글--%>
                        <div class="question hide-object border-bottom-gray" data-ques-no="${question.quesNo}">
                          <div class=" pt-3 px-3" >
                            <div>
                              <div>
                                <%--title and close--%>
                                <div>
                                  <div class="d-flex justify-content-between position-relative">
                                    <%--Question title--%>
                                    <div class="question-text pe-2">
                                      <a class="hover-underline" href="/question/${question.quesNo}"><strong style="line-height: 1.6;">${question.question}</strong></a>
                                    </div>
                                    <%--Question close--%>
                                    <div class="position-absolute" style="top: -12px; right: -12px;">
                                      <div>
                                        <span class="hide-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-ques-no="${question.quesNo}" data-toggle="description" data-content="Hide">
                                          <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path d="m16.243 7.757-8.486 8.486m8.486 0L7.757 7.757" class="icon_svg-stroke" fill="none" fill-rule="evenodd" stroke-linecap="round" stroke="#666" stroke-width="1.5"></path>
                                          </svg>
                                        </span>
                                      </div>
                                    </div>
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
                                        <c:if test="${principal != question.userEmail}">
                                        <button class="answer-button px-2 rounded-pill bg-hover-whiten-light" style="margin-left: -0.5rem; height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;" data-ques-no="${question.quesNo}">
                                            <div class="d-flex justify-content-center align-items-center">
                                                <%--Answer icon--%>
                                              <div>
                                                <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                  <g stroke-width="1.5" fill="none" fill-rule="evenodd">
                                                    <path d="M18.571 5.429h0a2 2 0 0 1 0 2.828l-9.9 9.9-4.24 1.416 1.412-4.245 9.9-9.9h0a2 2 0 0 1 2.828 0Z" stroke="rgb(72, 148, 253)" stroke-linecap="round" stroke-linejoin="round"></path>
                                                    <path fill="#666" d="m4.429 19.571 2.652-.884-1.768-1.768z"></path>
                                                    <path d="M14.5 19.5h5v-5m-10-10h-5v5"  stroke="rgb(72, 148, 253)" stroke-linecap="round" stroke-linejoin="round"></path>
                                                  </g>
                                                </svg>
                                              </div>
                                                <%--Answer text--%>
                                              <div class="color-gray ms-1" style="font-size: 14px;">
                                                <div>Answer</div>
                                              </div>
                                            </div>
                                          </button>
                                        </c:if>

                                      </div>

                                      <%--Options--%>
                                      <div class="ms-auto d-flex">

                                        <%--Share button--%>
<%--                                        <div>--%>
<%--                                          <div>--%>
<%--                                            <span class="share-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-ques-no="${question.quesNo}" data-toggle="description" data-content="Copy Link">--%>
<%--                                              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
<%--                                                <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z" class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>--%>
<%--                                              </svg>--%>
<%--                                            </span>--%>
<%--                                          </div>--%>
<%--                                        </div>--%>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>

                      </c:forEach>

                      </div>

                      <c:if test="${fn:length(questions) <= 0}">
                        <div id="no-content" class="pb-2" style="width: 100%;">
                          <div class="pt-4 d-flex justify-content-center">
                            <img src="/static/img/noContent.png" alt="" height="100" width="100">
                          </div>
                          <div class="text-center color-gray-light pb-4" style="font-size: 13px;">등록된 질문이 없습니다.</div>
                        </div>
                      </c:if>
                    </div>
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
  <%@ include file="/WEB-INF/views/modals/answer.jsp" %>
  <%@ include file="/WEB-INF/views/templates/questionTemplate.jsp" %>

<input id="logged-in-user" type="hidden" value="${principal}">

  <%--JS--%>
  <script type="text/javascript" src="/static/js/utils.js"></script>
  <script type="text/javascript" src="/static/js/main.js"></script>
  <script type="text/javascript" src="/static/js/question.js"></script>
  <script type="text/javascript" src="/static/js/answer.js"></script>
  <script type="text/javascript" src="/static/js/getMoreQuestions.js"></script>
</body>
</html>
