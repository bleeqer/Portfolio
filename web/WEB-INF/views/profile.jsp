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
        <div class="d-flex justify-content-center mx-auto" style="width: 1002px; padding-top: 30px; padding-left: 24px; padding-right: 24px;">

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
                                    <h1 class="m-0">유동근</h1>
                                </div>
                            </div>

                            <%--User credential--%>
                            <div class="mt-2 color-dark-gray">
                                <div>
                                    Credential
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
                                <div class="py-3 px-2 color-dark-gray bg-hover-whiten-light position-relative color-red" style="font-size: 13px;">
                                    <div><b>Answers</b></div>
                                    <div class="rounded-left-top-pill rounded-right-top-pill bg-red position-absolute" style="height: 3px; width: 100%; left: 50%; bottom: 0; transform: translateX(-50%);"></div>
                                </div>
                                <div class="py-3 px-2 bg-hover-whiten-light" style="font-size: 13px;">
                                    <div><b>Questions</b></div>
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

                            <%--Content view--%>
                            <div class="py-3 px-4 d-flex justify-content-center">
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
<br/>
<br/>
<%--    <div class="container-fluid m-0" style="padding-top: 50px;">--%>
<%--        <div class="d-flex mx-auto pt-3 px-2" style="max-width: 1002px;">--%>

<%--        </div>--%>
<%--    </div>--%>
</body>
</html>
