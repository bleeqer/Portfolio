
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <sec:csrfMetaTags/>

    <script>
        const token = $("meta[name='_csrf']").attr("content")
        const header = $("meta[name='_csrf_header']").attr("content")
    </script>

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
            color: rgb(40, 40, 41);
            background-color: transparent;
        }

        html, body {
            box-sizing: border-box;
            height: 100%;
            width: 100%;
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

        input::placeholder {
            color: rgb(147, 151, 162);
        }


    </style>
</head>

<%--<%@ include file="/WEB-INF/views/modals/userRegisterForm.jsp" %>--%>
<body>

<div>
    <div class="color-light-black" style="height: 100%; width: 100%; background-image: url('/static/img/loginBackground.png'); background-repeat: no-repeat; background-size: cover; background-position: center;">
        <div class="d-flex justify-content-center align-items-center" style="padding: 237px 0; height: 100%; width: 100%;">
            <div class="rounded-1" style="box-shadow: 0 1px 1px rgb(0 0 0 / 4%);">

                <div class="rounded-1" style="background-color: #fff;">

                    <%--Banner--%>
                    <div class="mb-4">
                        <div>

                            <%--Logo--%>
                            <div class="text-center">
                                <div>
                                    <span>
                                        <svg width="175px" height="115px" viewBox="0 0 202 115">
                                            <g stroke="none" fill="rgb(185, 43, 39)" fill-rule="evenodd">
                                                <path d="M24.4,31.9 C37.1,31.9 49.1,41.8 49.1,56.2 C49.1,64.3 45.3,70.9 39.9,75.3 C41.5,77.9 43.5,79.7 45.9,79.7 C48.7,79.7 49.9,77.5 50.1,75.7 L50.1,75.7 L53.7,75.7 C53.9,78.1 52.7,87.3 42.7,87.3 C36.5,87.3 33.3,83.7 30.9,79.7 C28.9,80.1 26.7,80.5 24.5,80.5 C12.2,80.5 0,70.6 0,56.2 C0,41.8 12.2,31.9 24.4,31.9 Z M114.1,42.8 C124.3,42.8 132.5,50 132.6,60.7 C132.6,72 124.3,79.4 114.1,79.4 C104.2,79.4 95.6,71.9 95.6,60.7 C95.6,49.8 104.1,42.8 114.1,42.8 Z M181.4,42.8 C190.4,42.8 196,45.2 196,54.2 L196,54.2 L196,69.6 C196,72 196.8,73.2 198.8,73.2 C199.8,73.2 200.6,72.8 201,72.6 L201,72.6 L201.9,74.4 C201.1,75.8 198.5,78.4 193.7,78.4 C189.5,78.4 186.9,76.4 186.5,73.2 L186.5,73.2 L186.3,73.2 C184.3,76.8 180.7,79.2 175.5,79.2 C169.3,79.2 165.5,76 165.5,70.2 C165.5,58.8 181.4,62 186,54.4 L186,54.4 L186,52.6 C186,47.2 183.8,46 181.4,46 C174.2,46 177.4,54.4 171,54.4 C167.8,54.4 166.6,52.6 166.6,50.4 C166.6,46.2 171.8,42.8 181.4,42.8 Z M67.6,43.6 L67.6,67 C67.6,71.4 69.8,73.4 73,73.4 C75.6,73.4 78.4,72.2 79.8,69.4 L79.8,50 C79.8,48 79.2,47.2 77,47.2 L74.6,47.2 L74.6,43.6 L89.8,43.6 L89.8,69.3 C89.8,71.7 90.6,72.9 93.4,72.9 L93.8,72.9 L93.8,76.7 L80.2,78.9 L80.2,73.8 L80,73.8 C77.4,77.1 73.6,79.1 68.6,79.1 C62.4,79.1 57.8,75.9 57.8,67.3 L57.8,50 C57.8,48 57,47.2 54.8,47.2 L52.6,47.2 L52.6,43.6 L67.6,43.6 Z M157.9,43 C161.1,43 163.7,44.8 163.7,48.4 C163.7,51 162.5,53.6 158.9,53.6 C155.9,53.6 155.3,50.8 152.7,50.8 C150.5,50.8 148.7,53 148.7,56.2 L148.7,70.4 C148.7,73.6 149.5,74.6 153.1,74.6 L155.1,74.6 L155.1,78.4 L133.5,78.4 L133.5,74.7 L134.9,74.7 C138.5,74.7 138.9,73.7 138.9,70.5 L138.9,50 C138.9,48 137.9,47.2 135.7,47.2 L133.7,47.2 L133.7,43.6 L147.5,43.6 L148.1,50.8 L148.5,50.8 C149.9,45.6 154.1,43 157.9,43 Z M24.5,35.8 C15.3,35.8 11.3,42.7 11.3,56.1 C11.3,69.5 15.3,76.4 24.5,76.4 C26.2,76.4 27.7,76 28.9,75.6 C27.1,71.4 24.7,67.4 20.1,67.4 C19.3,67.4 18.5,67.6 17.7,68 L17.7,68 L16.3,65.2 C18.3,63.5 21,62.2 24.7,62.2 C30.5,62.2 33.5,65 35.9,68.6 C37.3,65.6 37.9,61.4 37.9,56.1 C37.9,42.7 33.9,35.8 24.5,35.8 Z M114.1,46.2 C109.3,46.2 106.5,51 106.5,60.6 C106.5,70.4 109.3,75.4 114.1,75.4 C119.3,75.4 121.3,70.4 121.5,60.6 C121.7,51.1 119.3,46.2 114.1,46.2 Z M185.9,58.6 C182.7,62.1 175.3,62.6 175.3,69 C175.3,72.2 177.3,74 179.9,74 C184.3,74 185.9,70.2 185.9,66 L185.9,66 Z"></path>
                                            </g>
                                        </svg>
                                    </span>
                                </div>
                            </div>

                            <%--Text--%>
                            <div class="text-center color-black-light" style="font-weight: bold; margin-top: -16px; font-size: 15px;">
                                A place to share knowledge and better understand the world
                            </div>
                        </div>
                    </div>

                    <%--Login view--%>
                    <div class="mb-4 d-flex" style="width: 700px;">

                        <%--Left side--%>
                        <div class="px-4 border-right-gray-light" style="width: 50%;">
                            <div class="border-bottom-gray-light" style="padding-bottom: 8px;">
                                <div id="sign-up-button" class="rounded-pill bg-hover-darken-light">
                                    <div class="d-flex justify-content-center">
                                        <span class="text-center color-gray-dark" style="line-height: 30px; font-size: 13px;">Sign up</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <%--Right side--%>
                        <div class="px-4" style="width: 50%;">

                            <%--Login--%>
                            <div class="mb-3">
                                <div class="pb-2 border-bottom-gray-light">
                                    <div class="color-gray-dark" style="font-size: 15px;">Login</div>
                                </div>
                            </div>

                            <form id="login-form" action="/user/login" method="POST">

                                <sec:csrfInput/>

                                <%--Email--%>
                                <div class="mb-3">
                                    <%--Email label--%>
                                    <div class="mb-1">
                                        <div class="color-gray-dark">
                                            <label for="login-email" style="font-size: 13px;">Email</label>
                                        </div>
                                    </div>

                                    <%--Email input--%>
                                    <div>
                                        <div class="d-flex align-items-center input-container color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                            <input class="input-focus"name="email" type="text" id="login-email" style="width: 100%; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your ID" autocomplete="off">
                                        </div>
                                    </div>
                                </div>

                                <%--Password--%>
                                <div class="mb-3">

                                    <%--Password label--%>
                                    <div class="mb-1">
                                        <div class="color-gray-dark">
                                            <label for="login-password" style="font-size: 13px;">Password</label>
                                        </div>
                                    </div>

                                    <%--Password input--%>
                                    <div>
                                        <div class="d-flex align-items-center input-container color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                            <input id="login-password" class="input-focus" name="password" type="password" style="width: 100%; outline: none; border: none; font-size: 15px;" placeholder="Password" autocomplete="off">
                                        </div>
                                    </div>
                                </div>
                                <div class="position-relative color-palette" style="height: 50px; font-size: 11px; line-height: 3;">
                                    <div id="login-message" class="position-absolute" style="width: 100%; display: none;">
                                        <div class="message position-absolute px-1 color-black-light" style="width: 100%; height: 50px;">

                                        </div>
                                        <div class="rounded-3 bg-danger bg-opacity-50 shadow-sm position-absolute" style="width: 100%; height: 33px;">

                                        </div>
                                    </div>
                                </div>

                                <%--Login--%>
                                <div class="mb-3">
                                    <div class="d-flex justify-content-between align-items-center">

                                        <%--Forgot password--%>
                                        <div>
                                            <div>
                                                <a class="hover-underline color-gray-light" data-bs-toggle="modal" data-bs-target="#findPW-modal" style="font-size: 13px;">Forgot password?</a>
                                            </div>
                                        </div>

                                        <%--Login button--%>
                                        <span id="login-button" class="d-flex justify-content-center align-items-center rounded-pill btn btn-primary py-1 px-3" style="height: 38px; transition: background-color 250ms;">
                                            <div style="line-height: 100%; font-size: 15px;">Login</div>
                                        </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <%--Footer--%>
                    <div class="pb-4">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/modals/signUp.jsp" %>
<%--<%@ include file="/WEB-INF/views/modals/findPW.jsp" %>--%>

<script src="/static/js/login.js"></script>

</body>
</html>