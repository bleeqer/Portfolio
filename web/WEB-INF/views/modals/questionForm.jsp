<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modal-question" class="modal">

    <form id="question-form" action="" method="POST" enctype="multipart/form-data">

        <input type="hidden" id="quesNo" name="quesNo">

        <input type="text" id="title" name="title">

        <%--    Editor로 사용할 태그    --%>
        <textarea name="content" id="content" style="width: 100%; height: 100%;"></textarea>

        <input type="text" id="writer" name="writer" style="display: none;" value="<sec:authentication property="principal.username"/>">

        <div id="form-footer">
            <span id="upload-button">Image</span>
            <button type="submit" id="question-submit" class="btn">Submit</button>
        </div>
<%--        이미지 업로드 input 태그(숨김)--%>
        <input type="file" multiple="multiple" name="image" id="image" style="display: none;" accept="image/*">
    </form>
</div>

<script>



    // 이미지 업로드 버튼 클릭 시 image input 태그 클릭 작동
    $("#upload-button").click(function () {
        $("#image").trigger("click")
    })

</script>

<script src="/js/imageUpload.js"></script>


