<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="answerPair" items="${answerPairs}">
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