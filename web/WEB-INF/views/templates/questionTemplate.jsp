<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="question" items="${questions}">

    <%--질문글--%>
    <div class="question-container" data-ques-no="${question.quesNo}">
        <div class="unanswered-question pt-3 px-3 border-bottom-gray">
            <div>
                <div>
                        <%--title and close--%>
                    <div>
                        <div class="d-flex justify-content-between position-relative">
                                <%--Question title--%>
                            <div class="pe-2">
                                <a class="hover-underline" href=""><b>${question.question}</b></a>
                            </div>
                                <%--Question close--%>
                            <div class="position-absolute" style="top: -12px; right: -12px;">
                                <div>
                                        <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">
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
                                asked 00 ago
                            </div>
                        </div>
                    </div>

                        <%--Question footer--%>
                    <div class="mt-1">
                        <div style="padding-top: 1px; padding-bottom: 1px;">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex">
                                        <%--Answer button--%>
                                    <button class="px-2 rounded-pill bg-hover-whiten-light" style="margin-left: -0.5rem; height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;" data-bs-toggle="modal" data-bs-target="#answer-modal">
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

                                        <%--Comment 버튼--%>
                                    <div role="button">

                                            <%--Container--%>
                                        <div class="d-inline">

                                                <%--Comment button--%>
                                            <div class="d-flex rounded-pill px-2 bg-hover-whiten-light">

                                                    <%--Comment icon--%>
                                                <span class="d-inline-block d-flex align-items-center justify-content-center" style="height: 38px;">
                                              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z" class="icon_svg-stroke icon_svg-fill" stroke="#666" stroke-width="1.5" fill="none"></path>
                                              </svg>
                                            </span>

                                                    <%--Comment count--%>
                                                <div class="d-flex align-items-center justify-content-center color-gray">
                                                    100
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                        <%--Pass button--%>
                                    <button class="px-2 rounded-pill bg-hover-whiten-light" style="height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;">
                                        <div class="d-flex justify-content-center align-items-center">
                                                <%--Answer icon--%>
                                            <div>
                                                <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                    <g fill="none" fill-rule="evenodd">
                                                        <path d="m11.828 9.314 3.9-3.9a2 2 0 1 1 2.828 2.829l-3.9 3.9m-3.535 3.535-2.464 2.464-4.241 1.416 1.412-4.244 2.465-2.465" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="square" stroke-linejoin="round"></path>
                                                        <path class="icon_svg-fill_as_stroke" fill="#666" d="m4.414 19.556 2.652-.884-1.768-1.767z"></path>
                                                        <path d="M4.636 5.636 18.5 19.5" class="icon_svg-stroke" stroke="#666" stroke-width="1.5" stroke-linecap="round"></path>
                                                    </g>
                                                </svg>
                                            </div>
                                                <%--Answer text--%>
                                            <div class="color-gray ms-1" style="font-size: 14px;">
                                                <div>Pass</div>
                                            </div>
                                        </div>
                                    </button>
                                </div>

                                    <%--Options--%>
                                <div class="ms-auto d-flex">

                                        <%--Share button--%>
                                    <div>
                                        <div>
                                            <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">
                                              <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z" class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>
                                              </svg>
                                            </span>
                                        </div>
                                    </div>

                                        <%--Three dots--%>
                                    <div>
                                          <span class="bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">
                                            <svg class="d-block" width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
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
            </div>
        </div>
    </div>

</c:forEach>