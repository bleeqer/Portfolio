<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication property="principal" var="principal"/>

<%--마지막 댓글 체크--%>
<span class="last-checker" style="display: none;" data-is-last="${isLast}"></span>

<c:forEach var="comment" items="${comments}">
    <div class="comment py-1 <c:if test="${comment.level == 1}">border-top-gray</c:if>"
         data-co-level="${comment.level}"
         data-ans-no="${comment.ansNo}"
         data-co-no="${comment.coNo}"
         data-parent-co-no="${comment.parentCoNo}"
         style="
         <c:if test="${comment.level > 1}">display: none;</c:if>
         <c:choose>
              <c:when test="${comment.level == 2}">padding-left: 42px</c:when>
              <c:when test="${comment.level > 2}">padding-left: calc(${(comment.level - 2) * 36}px + 42px)</c:when>
         </c:choose>;">

            <%--Comment node--%>
        <div class="d-flex flex-column">

                <%--Comment--%>
            <div class="d-flex">

                    <%--Profile pic--%>
                <div>
                    <div>
                        <img class="rounded-circle" src="${comment.userPhoto}"
                                <c:choose>
                                    <c:when test="${comment.level == 1}">width="36" height="36"</c:when>
                                    <c:otherwise>width="24" height="24"</c:otherwise>
                                </c:choose>
                             alt="" >
                    </div>
                </div>

                    <%--Comment content--%>
                <div class="ms-2" style="width: 100%;">

                        <%--Name and date--%>
                    <div>
                        <div>

                                <%--Name--%>
                            <span class="font-bold"
                                  style="font-size: 13px;">
                                                                                            <a href="">${comment.userName} · </a>
                                                                                        </span>

                                <%--Date--%>
                            <span class="color-gray-light"
                                  style="font-size: 13px;">
                                                                                            ${comment.regDate}
                                                                                        </span>
                        </div>
                    </div>

                        <%--Comment content--%>
                    <div class="comment-text" data-co-no="${comment.coNo}">
                        <div>
                            <p class="my-1"
                               style="font-size: 15px;">
                                ${comment.answerComment}
                            </p>
                        </div>
                    </div>

                        <%--Footer--%>
                    <div class="comment-footer" data-co-no="${comment.coNo}">

                            <%--Container--%>
                        <div class="d-flex justify-content-center align-items-center py-1"
                             style="height: 46px;">

                                <%--div.Upvote/Downvote, Comment 버튼--%>
                            <div class="d-flex justify-content-center align-items-center">

                                    <%--Upvote/Downvote 버튼--%>
                                <div class="comment-like-container me-1 d-flex justify-content-center align-items-center">

                                        <%--Container--%>
                                    <div class="d-flex rounded-pill"
                                         role="button"
                                         style="background-color: rgba(255, 255, 255, 0.05);">
                                            <%--Upvote 버튼--%>
                                        <div class="comment-like-button d-flex justify-content-center align-items-center bg-hover-whiten-light rounded-left-top-pill rounded-left-bottom-pill"
                                             style="height: 30px; padding: 0px 10px;" data-co-no="${comment.coNo}">
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

                                                <div class="comment-like-cnt d-flex align-items-center justify-content-center"
                                                     style="font-size: 13px; margin-left: 2px;" data-co-no="${comment.coNo}">
                                                    <c:if test="${comment.likes > 0}">
                                                        ${comment.likes}
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                            <%--Downvote 버튼--%>
                                        <div class="comment-dislike-button d-flex justify-content-center align-items-center bg-hover-whiten-light px-2 rounded-right-top-pill rounded-right-bottom-pill"
                                             style="height: 30px; padding: 0px 10px;" data-co-no="${comment.coNo}">
                                            <div class="d-flex">
                                                      <span class="d-inline-block d-flex align-items-center justify-content-center">
                                                        <svg width="20" height="20" viewBox="0 0 24 24"
                                                             xmlns="http://www.w3.org/2000/svg">
                                                          <path d="m12 20 9-11h-6V4H9v5H3z"
                                                                class="icon_svg-stroke icon_svg-fill"
                                                                stroke="#666" fill="none" stroke-width="1.5"
                                                                stroke-linejoin="round"></path>
                                                        </svg>
                                                      </span>
                                            </div>

                                            <div class="comment-dislike-cnt d-flex align-items-center justify-content-center"
                                                 style="font-size: 13px; margin-left: 2px;" data-co-no="${comment.coNo}">
                                                <c:if test="${comment.dislikes > 0}">
                                                    ${comment.dislikes}
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                    <%--Comment 버튼--%>
                                <div class="reply-button"
                                     role="button">

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
                                            <div class="comment-count d-flex align-items-center justify-content-center color-gray">
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
                                                        class="icon_svg-stroke" stroke="#666" fill="none"
                                                        stroke-width="1.5" stroke-linejoin="round"></path>
                                                </svg>
                                              </span>
                                    </div>
                                </div>
                                    <%--Three dots--%>
                                <sec:authorize access="isAuthenticated()">
                                    <c:if test="${principal == comment.userEmail}">
                                        <div>
                                            <a tabindex="0" class="comment-option-button bg-hover-whiten-light rounded-circle d-inline-block d-flex align-items-center justify-content-center" style="width: 38px; height: 38px;">
                                                <svg class="d-block" width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                  <path d="M5 14a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Zm7 0a2 2 0 1 1 0-4 2 2 0 0 1 0 4Z" class="icon_svg-stroke" stroke-width="1.5" stroke="#666" fill="none">
                                                  </path>
                                                </svg>
                                                <div class="comment-option-popover-content" style="display: none;">
                                                    <div class="d-flex comment-popover-item" data-co-level="${comment.level}" data-ans-no="${comment.ansNo}" data-co-no="${comment.coNo}" data-parent-co-no="${comment.parentCoNo}" data-option-type="Edit">
                                                        <div class="py-1 px-3">Edit</div>
                                                    </div>
                                                    <div class="d-flex comment-popover-item" data-co-level="${comment.level}" data-ans-no="${comment.ansNo}" data-co-no="${comment.coNo}" data-parent-co-no="${comment.parentCoNo}" data-option-type="Delete">
                                                        <div class="py-1 px-3">Delete</div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </c:if>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>

                    <div class="comment-edit-container" style="display: none;" data-co-no="${comment.coNo}">
                        <div>
                            <div class="rounded-6 bg-black px-3 border-gray mt-1" style="width: 100%; padding-top: 7px; padding-bottom: 7px;">
                                <form class="comment-edit-form" action="" style="line-height: 0;" data-co-no="${comment.coNo}">
                                    <input id="comment-edit-input" type="text" name="answerComment" style="height: 22px; border: none; outline: none; width: 100%; font-size: 15px; line-height: 22px;">
                                    <input id="co-no" type="hidden" name="coNo" value="">
                                    <input id="user-email" type="hidden" name="userEmail" value="">
                                    <sec:csrfInput/>
                                </form>
                            </div>
                            <div class="d-flex my-2" style="width: 100%;">
                                <div class="d-flex ms-auto">
                                    <div class="comment-edit-cancel-button rounded-pill d-flex px-3 justify-content-center align-items-center font-bold ms-1" style="height: 30px; font-size: 13px; border: none;" data-co-level="${comment.level}" data-co-no="${comment.coNo}" data-parent-co-no="${comment.parentCoNo}" data-ans-no="${comment.ansNo}">
                                        Cancel
                                    </div>
                                    <div class="comment-edit-submit-button rounded-pill btn-primary d-flex px-3 justify-content-center align-items-center font-bold ms-1" style="height: 30px; font-size: 13px; border: none;" data-co-no="${comment.coNo}">
                                        Edit
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>

        </div>

        <div class="reply-input-container mx-1" style="padding-left: 42px; display: none;">
            <div class="d-flex align-items-center">
                <div class="rounded-pill bg-black px-3 border-gray" style="width: 100%; padding-top: 7px; padding-bottom: 7px;">
                    <form class="comment-form" action="" style="line-height: 0;">
                        <input type="text" name="answerComment" placeholder="Add a reply..." style="height: 22px; border: none; outline: none; width: 100%; font-size: 15px; line-height: 22px;">
                        <input type="hidden" name="ansNo" value="${comment.ansNo}">
                        <input type="hidden" name="parentCoNo" value="${comment.coNo}">
                        <sec:csrfInput/>
                    </form>
                </div>
                <div class="add-comment-button rounded-pill btn-primary d-flex px-3 justify-content-center align-items-center font-bold ms-1" style="height: 30px; font-size: 13px; border: none;" data-co-level="${comment.level}" data-co-no="${comment.coNo}" data-parent-co-no="${comment.parentCoNo}" data-ans-no="${comment.ansNo}">
                    Reply
                </div>
            </div>
        </div>

            <%--View more replies--%>
        <div class="view-more-reply-container" style="display: none;" data-co-no="${comment.coNo}" data-parent-co-no="${comment.parentCoNo}">
            <div class="pb-3 px-3">
                <button class="view-more-replies btn d-flex justify-content-center align-items-center border-gray rounded-3 bg-black-light bg-darken-light bg-hover-darken py-0 px-3"
                        style="height: 30px; width: 100%; font-weight: 500;">
                    <div class="d-flex align-items-center">

                            <%--Text--%>
                        <div class="color-gray"
                             style="font-size: 13px;">
                            View more replies
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
</c:forEach>