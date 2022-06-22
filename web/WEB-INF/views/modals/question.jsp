<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="question-modal" tabindex="-1" class="modal fade" aria-hidden="true" aria-labelledby="modal-title">
    <div class="modal-dialog border-grey rounded-3" style="top: calc(50% - 25px); transform: translateY(-50%); background-color: rgb(24, 24, 24) !important; max-height: 70vh; min-width: 680px; max-width: 90vw; width: 730px;">
        <div class="modal-content px-3" style="background-color: transparent; max-height: 100%;">
            <div class="modal-header border-bottom-blue-3 mb-3" style="margin-left: -16px; margin-right: -16px;">
                <h4 class="modal-title mx-auto" id="modal-title">Ask</h4>
            </div>
            <div class="border-bottom-grey">
                <%--Tips--%>
                <div class="p-3 color-blue bg-blue rounded-2 d-flex align-items-center">
                    <div class="d-flex align-items-center">
                        <div style="margin-bottom: -14px;">
                            <div class="mb-1"><strong>Tips</strong></div>
                            <ul style="font-size: 14px;">
                                <li class="mb-1">이미 존재하는 질문이 아닌지 확인해주세요.</li>
                                <li class="mb-1">질문 내용은 간결하고 정확히 해주세요.</li>
                                <li class="mb-1">맞춤법과 철자를 확인해주세요.</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <%--Category--%>
                <div class="d-flex">
                    <div class="pt-3 me-2">
                        <select id="category-select" class="rounded-1 py-0 px-1" aria-label="select" style="height: 30px; border: none !important; color: rgb(213, 214, 214);
                         background-color: rgb(13,110,253) !important;">
                            <option selected hidden value="1">Choose category</option>
                        </select>
                    </div>

                </div>

                <%--Question textarea--%>
                <div>
                    <form id="question-form" action="/question/create" method="POST">
                        <div class="d-flex justify-content-center align-items-center modal-body border-bottom-grey hover-border-bottom-color-blue border-top-0" style="width: 100%; padding: 0; transition: border-bottom 250ms; transition-timing-function: ease-in-out;">
                            <textarea id="question-textarea" name="question" class="mt-3 mb-1 px-2 py-0" style="line-height: 1.4; font-size: 14px; border: 0; outline: none; overflow: hidden; overflow-wrap: break-word; width: 100%; resize: none; max-height: 130px;" placeholder="질문을 입력해주세요."></textarea>
                        </div>
                        <input id="ques-no" type="hidden" name="quesNo" value="">
                        <input id="user-email" type="hidden" name="userEmail" value="">
                        <input id="category-code" type="hidden" name="categoryCode" value="">
                        <sec:csrfInput/>
                    </form>
                </div>
            </div>


            <div class="modal-footer ms-auto border-top-0 mt-auto">
                <div class="d-flex align-items-center" style="width: 100%;">
                    <div class="d-flex justify-content-center align-items-center">
                        <%--Cancel--%>
                        <button class="px-3 rounded-pill bg-hover-whiten me-1" data-bs-dismiss="modal" style="all: unset; transition: background-color 250ms; height: 38px;">Cancel</button>

                        <%--Post--%>
                        <button id="add-question-button" class="btn btn-primary rounded-pill hover-opacity-9">Add question</button>
                        <button id="edit-question-button" class="btn btn-primary rounded-pill hover-opacity-9" style="display: none;">Edit question</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
