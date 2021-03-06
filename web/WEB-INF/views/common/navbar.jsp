<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authentication property="principal" var="principal"/>

<div class="navbar-container position-fixed container-fluid bg-dark border-bottom border-color-dark shadow" style="z-index: 3; top: 0;">
    <div class="bg-dark"></div>
    <nav class="navbar navbar-dark py-0 mx-auto flex-nowrap" style="max-width: 1002px; height: 50px;">

        <%--Logo--%>
        <a href="#" class="navbar-brand px-2">LOGO</a>

        <%--Home button--%>
        <a href="/" class="nav-pair nav-item d-flex align-items-center justify-content-center rounded-1 position-relative" style="height: 50px; width: 60px; min-width: 60px;" data-toggle="description" data-content="Home">
            <div>
  <span class="d-inline-block">
    <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
      <path d="M14.5 17.5a2.5 2.5 0 1 0-5 0v5a.625.625 0 0 1-.533.618l-.092.007H4.5a.625.625 0 0 1-.618-.533l-.007-.092v-9.375H.75a.625.625 0 0 1-.505-.995l.063-.072L11.558.808a.625.625 0 0 1 .797-.072l.087.072 11.25 11.25c.37.37.145.989-.347 1.06l-.095.007h-3.125V22.5a.625.625 0 0 1-.533.618l-.092.007h-4.375a.625.625 0 0 1-.625-.625v-5Z" class="icon_svg-fill_as_stroke" fill="#B1B3B6"></path>
    </svg>
  </span>
            </div>
            <div class="bg-hover-whiten position-absolute rounded-2" style="height: 45px; min-width: 60px; transition: background-color 250ms;"></div>
            <div class="under-red rounded-left-top-pill rounded-right-top-pill bg-red position-absolute hidden" style="height: 3px; width: 44px; left: 50%; bottom: 0; transform: translateX(-50%);"></div>
        </a>

        <%--Qeustion button--%>
        <a href="/questions" class="nav-question nav-item d-flex align-items-center justify-content-center rounded-1 position-relative" style="height: 50px; width: 60px; min-width: 60px;" data-toggle="description" data-content="Questions">
            <div>
      <span class="d-inline-block">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
          <path class="" d="M20.582 1.469a2.757 2.757 0 0 1 1.369 4.468l-.134.143L7.594 20.299a.615.615 0 0 1-.129.099l-.073.036-1.238.514.006.006-.1.033-3.82 1.59a.615.615 0 0 1-.662-.116l-.058.019.019-.058a.615.615 0 0 1-.147-.569l.031-.093 1.592-3.831.031-.089.005.005.515-1.237a.637.637 0 0 1 .081-.141l.054-.061L17.92 2.182a2.756 2.756 0 0 1 2.662-.713zm.918 8.406c.314 0 .574.231.618.533l.007.092v11a.624.624 0 0 1-.533.618l-.092.007h-11a.625.625 0 0 1-.092-1.243l.092-.007h10.375V10.5c0-.314.231-.574.533-.618l.092-.007zm-2.577-6.916-.119.107L4.673 17.201l-.666 1.6 1.19 1.19 1.601-.665 14.136-14.13c.304-.304.46-.72.439-1.14l-.016-.158-.033-.157a1.504 1.504 0 0 0-2.4-.782zM13.5 1.875a.625.625 0 0 1 .092 1.243l-.092.007H3.124L3.125 13.5a.624.624 0 0 1-.533.618l-.092.007a.624.624 0 0 1-.618-.533l-.007-.092v-11c0-.314.231-.574.533-.618l.092-.007h11z" fill="#B1B3B6"></path>
        </svg>
      </span>
            </div>
            <div class="bg-hover-whiten position-absolute rounded-2" style="height: 45px; min-width: 60px; transition: background-color 250ms;"></div>
            <div class="under-red rounded-left-top-pill rounded-right-top-pill bg-red position-absolute hidden" style="height: 3px; width: 44px; left: 50%; bottom: 0; transform: translateX(-50%);"></div>
        </a>

        <%--Notif button--%>
<%--        <a href="/notifications" class="nav-item d-flex align-items-center justify-content-center rounded-1 position-relative" style="height: 50px; width: 60px; min-width: 60px;" data-toggle="description" data-content="Notifications">--%>
<%--            <div>--%>
<%--    <span class="d-inline-block">--%>
<%--      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">--%>
<%--        <path class="" d="M7.526 20.785H2.79a.625.625 0 0 1-.625-.625v-2.87c0-.283.19-.53.463-.604a3.359 3.359 0 0 0 2.093-1.655c.207-.456.349-1.126.426-1.944a18.81 18.81 0 0 0 .044-2.578l-.025-.44a6.84 6.84 0 0 1 4.402-6.386 2.54 2.54 0 0 1 4.133-2.658 2.54 2.54 0 0 1 .692 2.678 6.866 6.866 0 0 1 4.346 6.415l-.022.381a19.11 19.11 0 0 0 .046 2.585c.076.811.214 1.476.415 1.932a3.376 3.376 0 0 0 2.079 1.657.625.625 0 0 1 .459.603v2.885c0 .345-.28.625-.625.625h-4.616a4.93 4.93 0 0 1-8.948 0zm1.432 0a3.68 3.68 0 0 0 6.084 0H8.958zm-1.155-1.25a.62.62 0 0 1 .273 0h7.847a.62.62 0 0 1 .273 0h4.269v-1.81a4.62 4.62 0 0 1-2.402-2.143l-.018-.037c-.272-.605-.439-1.399-.527-2.346a20.211 20.211 0 0 1-.05-2.758l.022-.371a5.594 5.594 0 0 0-4.219-5.418A6.03 6.03 0 0 0 12 4.485h-.089a6.048 6.048 0 0 0-1.291.186.713.713 0 0 1-.12.019c-2.358.657-4.083 2.813-4.087 5.331l.026.43c.042.89.037 1.847-.048 2.754-.089.945-.257 1.738-.548 2.377a4.623 4.623 0 0 1-2.428 2.161v1.793h4.389zm2.969-16.189a6.907 6.907 0 0 1 1.104-.111h.133c.402.006.796.048 1.178.121a1.29 1.29 0 0 0-2.078-1.409 1.29 1.29 0 0 0-.337 1.4z" fill="#B1B3B6"></path>--%>
<%--      </svg>--%>
<%--    </span>--%>
<%--            </div>--%>
<%--            <div class="bg-hover-whiten position-absolute rounded-2" style="height: 45px; min-width: 60px; transition: background-color 250ms;"></div>--%>
<%--            <div class="under-red rounded-left-top-pill rounded-right-top-pill bg-red position-absolute hidden" style="height: 3px; width: 44px; left: 50%; bottom: 0; transform: translateX(-50%);"></div>--%>
<%--        </a>--%>

        <%--Search box--%>
        <div class="nav-item ms-auto me-2 bg-black rounded-pill border border-color-gray hover-border-color-blue input-container position-relative" style="min-width: 150px; transition: min-width 250ms;">
            <form id="search-form" action="" method="GET">
                <div class="d-flex justify-content-between py-1 px-2" style="transition: background-color 250ms; height: 36px;">

                    <div class="d-flex justify-content-center" style="width: 100%;">
                        <%--Search bar--%>
                        <div class="d-flex ms-1" style="width: 100%;">
                            <input id="keyword" class="search-input input-focus justify-content-center" type="text" name="searchKeyword" style="background-color: transparent; border: none; min-height: 26px; font-size: 13px; width: 100%; outline: none; transition: visibility 250ms, opacity 250ms;" placeholder="Search..." autocomplete="off">
<%--                            <input id="category-code" type="hidden" name="categoryCode" value="${selectedCategory}">--%>
                            <%--Search focus overlay--%>
                            <div class="position-fixed bg-transparent-dark search-overlay hidden"></div>
                        </div>
                    </div>
                    <div class="d-flex">
                        <%--Magnifying glass--%>
                        <span id="search-button" class="d-flex justify-content-center">
            <svg class="my-auto me-1 search-button cursor-pointer" width="20" height="20" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
              <path d="M10.5 18a7.5 7.5 0 1 1 0-15 7.5 7.5 0 0 1 0 15Zm10.45 2.95L16 16l4.95 4.95Z" class="" stroke="#666" stroke-width="1.5" fill="none" stroke-linecap="round" stroke-linejoin="round"></path>
            </svg>
          </span>
                    </div>
                </div>
            </form>
            <form id="search-data" action="" style="display: none;">
                <input class="ques-no" type="hidden" name="quesNo">
                <input id="search-keyword" type="hidden" value="${keyword}">
<%--                <input id="category-code" type="hidden" name="categoryCode" value="${selectedCategory}">--%>
            </form>

        </div>

        <%--User dropdown--%>
        <div class="dropdown cursor-pointer bg-hover-whiten rounded-2 position-relative d-flex justify-content-center align-items-center user-profile" style="height: 45px; width: 60px; min-width: 60px; transition: background-color 250ms;">
            <div id="user-dropdown" class="d-flex justify-content-center align-items-center" data-bs-toggle="dropdown" style="width: 100%; height: 100%;">
                <img id="navbar-profile" class="rounded-circle overflow-hidden" src="/static/img/user.png" alt="" style="width: 36px; height: 36px;"/>
            </div>
            <ul class="dropdown-menu dropdown-menu-center bg-dark border-gray" style="overflow: hidden; background-color: black;">
                <sec:authorize access="isAuthenticated()">
                    <li><a class="dropdown-item color-gray-light bg-hover-whiten-light d-flex justify-content-start align-items-center py-3" href="/user/profile/${principal}" style="line-height: 100%; font-size: 14px;">My Page</a></li>
                    <div class="border-bottom-gray"></div>
                    <li><span class="dropdown-item log-out color-gray-light bg-hover-whiten-light justify-content-start align-items-center py-2 font-bold" href="" style="line-height: 100%; font-size: 11px;">
                        Log out

                    </span>
                        <form id="logout-form" action="/user/logout" method="POST" style="display: none;">
                            <sec:csrfInput/>
                        </form>
                    </li>

                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li><a class="dropdown-item color-gray-light bg-hover-whiten-light d-flex justify-content-start align-items-center py-3" href="/user/login" style="line-height: 100%; font-size: 14px;">Login</a></li>

                </sec:authorize>
                 </ul>
<%--            <div class="bg-hover-whiten position-absolute rounded-2" style="height: 45px; min-width: 60px; transition-property: background-color; transition-duration: 250ms;"></div>--%>
        </div>
        <sec:authorize access="isAuthenticated()">
            <div class="px-2">
                <a id="ask-question-button" class="btn btn-danger rounded-pill py-1 font-bold" data-bs-toggle="modal" data-bs-target="#question-modal" style="font-size: 15px; min-width: 120px;">Ask question</a>
            </div>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <div class="px-2">
                <a id="" class="login-first btn btn-danger rounded-pill py-1 font-bold" style="font-size: 15px; min-width: 120px;">Ask question</a>
            </div>
        </sec:authorize>
        <%--Ask question button--%>


    </nav>
</div>

<%--Popover--%>


<script src="/static/js/navbar.js"></script>
