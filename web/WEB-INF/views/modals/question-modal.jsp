<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="question-modal" tabindex="-1" class="modal fade" aria-hidden="true" aria-labelledby="modal-title">
    <%--    style="left: 50%; top: 50%; transform: translate(-50%, -50%); min-height: 350px; max-height: 70vh; min-width: 680px; max-width: 90vw; width: 730px;" role="dialog"--%>
    <div class="modal-dialog border-grey rounded-3" style="top: calc(50% - 25px); transform: translateY(-50%); background-color: rgb(24, 24, 24) !important; min-height: 350px; max-height: 70vh; min-width: 680px; max-width: 90vw; width: 730px;">
        <div class="modal-content px-3" style="background-color: transparent; height: 100%;">
            <div class="modal-header border-bottom-blue-3 mb-3" style="margin-left: -16px; margin-right: -16px;">
                <h4 class="modal-title mx-auto" id="modal-title">Ask</h4>
            </div>
            <div class="border-bottom-grey"  style="height: 450px;">
                <%--Tips--%>
                <div class="p-3 color-blue bg-blue rounded-2 d-flex align-items-center">
                    <div class="d-flex align-items-center">
                        <div style="margin-bottom: -14px;">
                            <div class="mb-1"><b>Tips</b></div>
                            <ul style="font-size: 14px;">
                                <li class="mb-1">이미 존재하는 질문이 아닌지 확인해주세요.</li>
                                <li class="mb-1">질문 내용은 간결하고 정확히 해주세요.</li>
                                <li class="mb-1">맞춤법과 철자를 확인해주세요.</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <%--Category--%>
                <div>
                    <div>

                    </div>
                </div>

                <%--Question textarea--%>
                <div>
                    <form action="">
                        <div class="d-flex justify-content-center align-items-center modal-body border-bottom-grey hover-border-bottom-color-blue border-top-0" style="width: 100%; padding: 0; transition: border-bottom 250ms; transition-timing-function: ease-in-out;">
                            <textarea id="question-textarea" class="mt-3 mb-1 px-2" style="all: unset; line-height: 1.4; font-size: 14px; min-height: 28px; height: 28px; overflow: hidden; overflow-wrap: break-word; width: 100%; resize: none; max-height: 250px;" placeholder="질문을 입력해주세요."></textarea>
                        </div>
                    </form>
                </div>
            </div>


            <div class="modal-footer ms-auto border-top-0 mt-auto">
                <div class="d-flex align-items-center" style="width: 100%;">
                    <%--Image upload--%>
<%--                    <div>--%>
<%--                        <div class="hover-border-color-blue d-flex justify-content-center align-items-center rounded-2" style="width: 30px; height: 30px; border: 1px solid transparent; transition: border-color 250ms !important;">--%>
<%--                            <span>--%>
<%--                                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24" height="24" viewBox="0 0 24 24"><defs><path d="M5 4.5v14H2V.5h16.5v4H5z" id="a"></path></defs><g fill="#B1B3B6" fill-rule="evenodd" class="icon_svg-fill_as_stroke"><g fill-rule="nonzero"><path d="M8 7a.5.5 0 0 0-.5.5v12a.5.5 0 0 0 .5.5h12a.5.5 0 0 0 .5-.5v-12A.5.5 0 0 0 20 7H8zm0-1.25h12a1.75 1.75 0 0 1 1.75 1.75v12A1.75 1.75 0 0 1 20 21.25H8a1.75 1.75 0 0 1-1.75-1.75v-12A1.75 1.75 0 0 1 8 5.75zM17.5 9a1 1 0 1 0 0 2 1 1 0 1 0 0-2zm0-1.25a2.25 2.25 0 1 1 0 4.5 2.25 2.25 0 1 1 0-4.5z"></path><path d="M7.511 16.316V20h13v-3.682c-1.73-.926-2.81-1.389-3.241-1.389-.647 0-2.606 1.388-3.257 1.389s-2.609-2.299-3.252-2.299c-.429 0-1.512.766-3.25 2.298zm6.674-1.353.867-.443c1.296-.69 1.629-.842 2.217-.842.732 0 1.874.489 3.831 1.537a1.25 1.25 0 0 1 .66 1.102V20a1.25 1.25 0 0 1-1.25 1.25h-13A1.25 1.25 0 0 1 6.261 20v-3.684a1.25 1.25 0 0 1 .423-.938c2.065-1.82 3.183-2.61 4.077-2.61.523 0 .911.21 1.443.613.271.205.489.392 1.007.849l.866.732.041.031.067-.03z"></path></g><mask id="b" fill="#B1B3B6"><use xlink:href="#a"></use></mask><path d="M4.5 3.5A.5.5 0 0 0 4 4v12a.5.5 0 0 0 .5.5h12a.5.5 0 0 0 .5-.5V4a.5.5 0 0 0-.5-.5h-12zm0-1.25h12A1.75 1.75 0 0 1 18.25 4v12a1.75 1.75 0 0 1-1.75 1.75h-12A1.75 1.75 0 0 1 2.75 16V4A1.75 1.75 0 0 1 4.5 2.25z" fill-rule="nonzero" mask="url(#b)"></path></g>--%>
<%--                                </svg>--%>
<%--                            </span>--%>
<%--                        </div>--%>
<%--                    </div>--%>

                    <div class="d-flex justify-content-center align-items-center">
                        <%--Cancel--%>
                        <button class="px-3 rounded-pill bg-hover-whiten me-1" data-bs-dismiss="modal" style="all: unset; transition: background-color 250ms; height: 38px;">Cancel</button>

                        <%--Post--%>
                        <button class="btn btn-primary rounded-pill hover-opacity-9">Add question</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    textarea {
        resize: none;
    }
</style>



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



    // 이미지 업로드 버튼 클릭 시 image input 태그 클릭 작동
    $('#upload-button').click(function () {
        $('#image').trigger('click')
    })

</script>

<script>
    $( document ).ready(function() {
        function isOverflown(element) {
            return element.prop('scrollHeight') > element.height()
        }

        // 질문 Modal Window textarea 자동 높이 조절
        $('#question-textarea').on('keyup', function () {
            $(this).height('26')
            let scHeight = $(this).prop('scrollHeight')
            $(this).height(scHeight)
        })

        // 질문 Modal Window close 시 form 내용 초기화
        $('#question-modal').on('hidden.bs.modal', function () {
            $(this).find('form').trigger('reset')
        })
    })


</script>

<script src="/js/imageUpload.js"></script>


