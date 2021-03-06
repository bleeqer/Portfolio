<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="answer-modal" tabindex="-1" class="modal fade" aria-hidden="true" aria-labelledby="modal-title">
    <div class="modal-dialog border-grey rounded-3" style="top: calc(50% - 25px); transform: translateY(-50%); background-color: rgb(24, 24, 24) !important; min-height: 350px; max-height: 70vh; min-width: 680px; max-width: 90vw; width: 730px;">
        <div class="modal-content px-3 pb-1" style="background-color: inherit; height: 100%;">

            <div class="pt-4 border-bottom-grey">

                <%--Profile--%>
                <div class="d-flex">
                        <%--프로필 사진--%>
                        <div class="me-3 position-relative">
                            <div>
                                <img id="user-img" class="rounded-circle border border-color-whiten" src="" alt="" width="50" height="50">
                            </div>
                            <div class="bg-hover-darken position-absolute" style="inset: 0;">
                            </div>
                        </div>

                        <%--프로필 정보--%>
                        <div class="d-flex flex-column">

                            <%--이름--%>
                            <div class="my-auto font-size-1"><a class="text-decoration-none link-default-color hover-underline h6" href=""><strong id="user-name"></strong></a></div>

                            <%--상세--%>
                            <div id="user-occupation" class="my-auto color-grey font-size-1"></div>

                        </div>
                    </div>

                <%--Question content--%>
                <div class="pt-3">
                    <div class="d-flex align-items-center" style="font-size: 18px;">
                        <h5><strong id="asked-question"></strong></h5>
                    </div>
                </div>

                <%--Answer textarea--%>
                <div>
                    <form id="answer-form" action="">
                        <div class="d-flex justify-content-center align-items-start modal-body hover-border-bottom-color-blue border-top-0" style="width: 100%; min-height: 220px; height: 100%; padding: 0; transition: border-bottom 250ms; transition-timing-function: ease-in-out;">
                            <textarea id="answer-textarea" name="answer" class="mt-3 mb-1 px-2" style="width: 100%; height: 220px; outline: none; border: none;" placeholder="답변을 입력해주세요."></textarea>
                        </div>
                        <input id="ans-no" type="hidden" name="ansNo">
                        <input id="ques-no" type="hidden" name="quesNo">
                        <input id="image" type="file" style="display: none;">
                        <input id="image-path" type="hidden" name="imagePath">
                        <sec:csrfInput/>
                    </form>
                </div>
                <div id="answer-images" style="visibility: hidden; height: 0 !important; width: 0 !important;">

                </div>
            </div>


            <div class="modal-footer border-top-0 mt-auto p-0" style="width: 100%;">
                <div class="d-flex align-items-center justify-content-between" style="width: 100%;">
                    <%--Image upload--%>
                    <div>
                        <div id="image-upload-button" class="hover-border-color-blue d-flex justify-content-center align-items-center rounded-2" style="width: 30px; height: 30px; border: 1px solid transparent; transition: border-color 250ms !important;">
                            <span>
                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24" height="24" viewBox="0 0 24 24"><defs><path d="M5 4.5v14H2V.5h16.5v4H5z" id="a"></path></defs><g fill="#B1B3B6" fill-rule="evenodd" class="icon_svg-fill_as_stroke"><g fill-rule="nonzero"><path d="M8 7a.5.5 0 0 0-.5.5v12a.5.5 0 0 0 .5.5h12a.5.5 0 0 0 .5-.5v-12A.5.5 0 0 0 20 7H8zm0-1.25h12a1.75 1.75 0 0 1 1.75 1.75v12A1.75 1.75 0 0 1 20 21.25H8a1.75 1.75 0 0 1-1.75-1.75v-12A1.75 1.75 0 0 1 8 5.75zM17.5 9a1 1 0 1 0 0 2 1 1 0 1 0 0-2zm0-1.25a2.25 2.25 0 1 1 0 4.5 2.25 2.25 0 1 1 0-4.5z"></path><path d="M7.511 16.316V20h13v-3.682c-1.73-.926-2.81-1.389-3.241-1.389-.647 0-2.606 1.388-3.257 1.389s-2.609-2.299-3.252-2.299c-.429 0-1.512.766-3.25 2.298zm6.674-1.353.867-.443c1.296-.69 1.629-.842 2.217-.842.732 0 1.874.489 3.831 1.537a1.25 1.25 0 0 1 .66 1.102V20a1.25 1.25 0 0 1-1.25 1.25h-13A1.25 1.25 0 0 1 6.261 20v-3.684a1.25 1.25 0 0 1 .423-.938c2.065-1.82 3.183-2.61 4.077-2.61.523 0 .911.21 1.443.613.271.205.489.392 1.007.849l.866.732.041.031.067-.03z"></path></g><mask id="b" fill="#B1B3B6"><use xlink:href="#a"></use></mask><path d="M4.5 3.5A.5.5 0 0 0 4 4v12a.5.5 0 0 0 .5.5h12a.5.5 0 0 0 .5-.5V4a.5.5 0 0 0-.5-.5h-12zm0-1.25h12A1.75 1.75 0 0 1 18.25 4v12a1.75 1.75 0 0 1-1.75 1.75h-12A1.75 1.75 0 0 1 2.75 16V4A1.75 1.75 0 0 1 4.5 2.25z" fill-rule="nonzero" mask="url(#b)"></path></g>
                                </svg>
                            </span>
                        </div>
                    </div>

                    <div class="d-flex justify-content-center align-items-center">
                        <%--Cancel--%>
                        <button class="px-3 rounded-pill bg-hover-whiten me-1" data-bs-dismiss="modal" style="all: unset; transition: background-color 250ms; height: 38px;">Cancel</button>

                        <%--Post--%>
                        <button id="add-answer-button" class="btn btn-primary rounded-pill hover-opacity-9">Add answer</button>
                        <button id="edit-answer-button" class="btn btn-primary rounded-pill hover-opacity-9" style="display: none;">Edit answer</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<%--    <form id="question-form" action="" method="POST" enctype="multipart/form-data">--%>

<%--        <input type="hidden" id="quesNo" name="quesNo">--%>

<%--        <input type="text" id="title" name="title">--%>

<%--        &lt;%&ndash;    Editor로 사용할 태그    &ndash;%&gt;--%>
<%--        <textarea name="content" id="content" style="width: 100%; height: 100%;"></textarea>--%>

<%--        <input type="text" id="writer" name="writer" style="display: none;">--%>

<%--        <div id="form-footer">--%>
<%--            <span id="upload-button">Image</span>--%>
<%--            <button type="submit" id="question-submit" class="btn">Submit</button>--%>
<%--        </div>--%>
<%--&lt;%&ndash;        이미지 업로드 input 태그(숨김)&ndash;%&gt;--%>
<%--        <input type="file" multiple="multiple" name="image" id="image" style="display: none;" accept="image/*">--%>
<%--    </form>--%>
</div>

<script>

    // var myModal = document.getElementById('question-modal')
    // var myInput = document.getElementById('myInput')
    //
    // myModal.addEventListener('shown.bs.modal', function () {
    //     myInput.focus()
    // })



    // // 이미지 업로드 버튼 클릭 시 image input 태그 클릭 작동
    // $('#upload-button').click(function () {
    //     $('#image').trigger('click')
    // })

</script>

<%--<script src="/js/imageUpload.js"></script>--%>


