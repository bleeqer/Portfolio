<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal" var="principal"/>

<div id="question-template" style="display: none;">
    <%--질문글--%>
    <div class="question hide-object border-bottom-gray">
        <div class=" pt-3 px-3" >
            <div>
                <div>
                    <%--title and close--%>
                    <div>
                        <div class="d-flex justify-content-between position-relative">
                            <%--Question title--%>
                            <div class="pe-2">
                                <a class="question-link hover-underline" href=""><strong class="question-text" style="line-height: 1.6;"></strong></a>
                            </div>
                            <%--Question close--%>
                            <div class="position-absolute" style="top: -12px; right: -12px;">
                                <div>
                                    <span class="hide-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-toggle="description" data-content="Hide">
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
                            <div class="question-reg-date">
                            </div>
                        </div>
                    </div>

                    <%--Question footer--%>
                    <div class="mt-1">
                        <div style="padding-top: 1px; padding-bottom: 1px;">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex">
                                    <%--Answer button--%>
                                    <button class="answer-button px-2 rounded-pill bg-hover-whiten-light" style="margin-left: -0.5rem; height: 38px; min-width: 38px; outline: none; border: none; transition: background-color 125ms;">
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
                                </div>

                                <%--Options--%>
                                <div class="ms-auto d-flex">

                                    <%--Share button--%>
                                    <div>
                                        <div>
                                        <span class="share-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-toggle="description" data-content="Copy Link">
                                          <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z" class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>
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
    </div>
</div>

