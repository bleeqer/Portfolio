<div id="modal-question" class="modal">

    <form id="question-form" action="" method="POST" enctype="multipart/form-data">

        <input type="hidden" id="bno" name="bno">

        <input type="text" id="title" name="title">

        <%--    Editor로 사용할 태그    --%>
        <textarea name="content" id="content" style="width: 100%; height: 100%;"></textarea>
        <input type="text" id="writer" name="writer" placeholder="">

        <div id="form-footer">
            <span id="upload-button">Image</span>
            <button type="submit" id="submit-button" class="btn">Submit</button>
        </div>
        <%--      이미지 업로드 input 태그(숨김)      --%>
        <input type="file" multiple="multiple" name="image" id="image" style="display: none;" accept="image/*">
    </form>
</div>
