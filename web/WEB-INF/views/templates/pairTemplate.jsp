<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal" var="principal"/>

<div id="pair-template" style="display: none;">
    <%--질문글--%>
    <div class="pair hide-object mb-3 mx-1" data-ques-no="">

        <%--배경색--%>
        <div class="rounded-3 shadow-sm border border-color-dark bg-dark position-relative bg-hover-whiten position-relative">
            <div class="QnA-section ps-4 pe-3 pt-3" style="height: 100%;">
                <%--Question close--%>
                <div class="position-absolute" style="top: 0; right: 0;">
                    <div>
                        <span class="hide-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-toggle="description" data-content="Hide">
                          <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m16.243 7.757-8.486 8.486m8.486 0L7.757 7.757" class="icon_svg-stroke" fill="none" fill-rule="evenodd" stroke-linecap="round" stroke="#666" stroke-width="1.5"></path>
                          </svg>
                        </span>
                    </div>
                </div>
                <div class="">

                    <%--질문글--%>
                    <div>

                        <%--질문글 body--%>
                        <div>
                            <div>

                                <%--Profile--%>
                                <div class="mb-2 d-flex">

                                    <%--Profile pic--%>
                                    <div class="me-3 position-relative">
                                        <div>
                                            <img class="answer-user-photo rounded-circle border border-color-whiten" src="/uploadedImages/" alt="" width="36" height="36">
                                        </div>
                                        <div class="bg-hover-darken position-absolute" style="inset: 0;">
                                        </div>
                                    </div>

                                    <%--프로필 정보--%>
                                    <div class="d-flex flex-column">

                                        <%--이름--%>
                                        <div class="my-auto"><a class="user-profile-link text-decoration-none link-default-color hover-underline h6" href="" style="font-size: 13px;"><strong class="answer-user-name"></strong></a></div>

                                        <%--상세--%>
                                        <div class="d-flex align-items-center my-auto color-gray text-center" style="font-size: 13px;"><div class="answer-user-occupation"></div><span class="px-1 text-center">·</span><div class="answer-reg-date d-flex text-center" style="font-size: 11px;"></div></div>
                                    </div>
                                </div>

                                <%--질문글 내용--%>
                                <div>
                                    <%--title and close--%>
                                    <div>
                                        <div class="d-flex justify-content-between position-relative">
                                            <%--Question title--%>
                                            <div class="pe-2">
                                                <a class="question-link hover-underline" href="/question/"><strong class="question-text" style="line-height: 1.6;"></strong></a>
                                            </div>

                                        </div>
                                    </div>
                                </div>

                                <%--답변글 내용--%>
                                <div class="answer position-relative" data-ans-no="">
                                    <div class="fade-post" style="">
                                        <div class="answer-text" style="font-size: 15px; line-height: 1.5rem;">
                                            <p></p>
                                            <div class="overflow-fade hidden" style=""></div>
                                        </div>
                                        <span class="readMore-button btn btn-primary rounded-pill hidden">More</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>


                    <%--Upvote/Downvote, Comment, Share, Option 버튼--%>
                    <div class="footer-container pe-1" data-ans-no="">

                        <%--Container--%>
                        <div class="d-flex justify-content-center align-items-center my-1"
                             style="height: 46px;">

                            <%--div.Upvote/Downvote, Comment 버튼--%>
                            <div class="d-flex justify-content-center align-items-center">

                                <%--Upvote/Downvote 버튼--%>
                                <div class="answer-like-container me-1 d-flex justify-content-center align-items-center">

                                    <%--Container--%>
                                    <div class="d-flex rounded-pill" role="button"
                                         style="background-color: rgba(255, 255, 255, 0.05); height: 30px;">

                                        <%--Like 버튼--%>
                                        <div class="answer-like-button d-flex justify-content-center align-items-center bg-hover-whiten px-2 rounded-left-top-pill rounded-left-bottom-pill" data-ans-no="">
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
                                                <div class="answer-like-cnt d-flex align-items-center justify-content-center"
                                                     style="font-size: 13px; margin-left: 2px;" data-ans-no="">

                                                </div>
                                            </div>
                                        </div>
                                        <%--Unlike 버튼--%>
                                        <div class="answer-dislike-button d-flex justify-content-center align-items-center bg-hover-whiten px-2 rounded-right-top-pill rounded-right-bottom-pill" data-ans-no="">
                                            <div class="d-flex">
                                                <div>
                                                                                <span class="d-inline-block d-flex align-items-center justify-content-center">
                                                                                    <svg width="20" height="20"
                                                                                         viewBox="0 0 24 24"
                                                                                         xmlns="http://www.w3.org/2000/svg">
                                                                                        <path d="m12 20 9-11h-6V4H9v5H3z"
                                                                                              class="icon_svg-stroke icon_svg-fill"
                                                                                              stroke="#666" fill="none"
                                                                                              stroke-width="1.5"
                                                                                              stroke-linejoin="round"></path>
                                                                                    </svg>
                                                                                </span>
                                                </div>
                                                <div class="answer-dislike-cnt d-flex align-items-center justify-content-center"
                                                     style="font-size: 13px; margin-left: 2px;" data-ans-no="">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <%--Comment 버튼--%>
                                <div class="comment-button"
                                     data-ans-no="" role="button">

                                    <%--Container--%>
                                    <div>

                                        <%--Comment button--%>
                                        <div class="d-flex rounded-pill px-2 bg-hover-whiten">

                                            <%--Comment icon--%>
                                            <span class="d-inline-block d-flex align-items-center justify-content-center"
                                                  style="height: 30px;">
                                                                                <svg width="24" height="24"
                                                                                     viewBox="0 0 24 24"
                                                                                     xmlns="http://www.w3.org/2000/svg">
                                                                                    <path d="M12.071 18.86c4.103 0 7.429-3.102 7.429-6.93C19.5 8.103 16.174 5 12.071 5s-7.429 3.103-7.429 6.93c0 1.291.379 2.5 1.037 3.534.32.501-1.551 3.058-1.112 3.467.46.429 3.236-1.295 3.803-.99 1.09.585 2.354.92 3.701.92Z"
                                                                                          class="icon_svg-stroke icon_svg-fill"
                                                                                          stroke="#666" stroke-width="1.5"
                                                                                          fill="none"></path>
                                                                                </svg>
                                                                            </span>

                                            <%--Comment count--%>
                                            <div class="comment-count d-flex align-items-center justify-content-center color-gray">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <%--Options--%>
                            <div class="ms-auto d-flex">

                                <%--Share button--%>
<%--                                <div>--%>
<%--                                    <div>--%>
<%--                                        <span class="share-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;" data-toggle="description" data-content="Copy Link">--%>
<%--                                          <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">--%>
<%--                                            <path d="m21 12-9.778-8v5.333c-1.926.45-3.5 1.172-4.722 2.167-1.222.995-2.389 2.495-3.5 4.5 1.333-.659 2.833-1.157 4.5-1.496 1.667-.34 2.908-.285 3.722.163V20L21 12Z" class="icon_svg-stroke" stroke="#666" fill="none" stroke-width="1.5" stroke-linejoin="round"></path>--%>
<%--                                          </svg>--%>
<%--                                        </span>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                            </div>

                        </div>
                    </div>

                </div>
            </div>

            <%--Comment section--%>
            <div class="comment-section"
                 data-ans-no="" style="display: none;">
                <div class="d-flex justify-content-between align-items-center px-3 py-2"
                     style="width: 100%; background-color: #202020;">

                    <%--Comment input--%>
                    <div class="rounded-3 bg-black ms-2 py-1 px-3"
                         style="width: 100%;">
                        <form class="comment-form" action="" data-ans-no="">
                            <input class="comment-text" type="text"
                                   name="answerComment"
                                   style="width: 100%; outline: none; border: none;"
                                   placeholder="Add a comment...">
                            <input type="hidden" name="ansNo" value="">
                            <input type="hidden" name="parentCoNo" value="0">
                            <%--                                                        <sec:authorize access="isAuthenticated()">--%>
                            <%--                                                            <input type="hidden" name="userEmail" value="">--%>
                            <%--                                                        </sec:authorize>--%>
                        </form>
                    </div>

                    <%--Add button--%>
                    <span class="add-comment-button btn btn-primary d-flex align-items-center justify-content-center rounded-pill"
                          style="font-size: 13px; height: 30px; margin-left: 4px;"
                          data-co-level="0" data-co-no="0" data-parent-co-no="0" data-ans-no="">
                    <div style="white-space: nowrap;">Add comment
                    </div>
                </span>
                </div>

                <%--Comments--%>
                <div class="bg-dark">
                    <div>

                        <%--Each comment--%>
                        <div class="comment-list px-3 pt-2">

                        </div>

                        <%--View more comments--%>
                        <div>
                            <div class="pb-3 px-3">
                                <button class="view-more-comments btn d-flex justify-content-center align-items-center border-gray rounded-pill bg-black-light bg-hover-darken-strong py-0 px-3"
                                        style="height: 30px; width: 100%; font-weight: 500;" data-ans-no="" data-parent-co-no="0">
                                    <div class="d-flex align-items-center">

                                        <%--Text--%>
                                        <div class="color-gray"
                                             style="font-size: 13px;">
                                            View more comments
                                        </div>

                                        <%--Arrow--%>
                                        <span>
                                <span>
                                    <svg class="d-flex" width="16" height="16" viewBox="0 0 24 24"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="m5 8.5 7 7 7.005-7" class="icon_svg-stroke"
                                              stroke="#666" stroke-width="1.5" fill="none"
                                              stroke-linecap="round"></path>
                                    </svg>
                                </span>
                            </span>
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>