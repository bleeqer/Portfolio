// 질문 popover option 초기화
function initQuestionPopover() {
    $(function () {
        $('.question-option-button').popover({
            trigger: 'focus',
            html: true,
            sanitize: false,
            content: $(this).find('.question-option-popover-content').html()
        })
    })

}

initQuestionPopover()

$('#question-list .question').last().removeClass('border-bottom-gray')


// 템플릿에 전달받은 댓글 데이터 세팅 후 화면에 삽입
function addQuestion (question) {

    let template = $('#question-template')

    // question data 속성 셋팅
    template.find('.question').attr('data-ques-no', question.quesNo)
    template.find('.question').addClass('border-bottom-gray')

    // question 셋팅
    template.find('.question-text').html(question.question)
    template.find('.question-reg-date').html(question.regDate)
    template.find('.answer-button').attr('data-ques-no', question.quesNo)
    template.find('.question-link').attr('href', '/question/' + question.quesNo)

    // comment option popover 셋팅
    template.find('.question-popover-item').attr('data-ques-no', question.quesNo)

    if ($('#logged-in-user').val() !== question.userEmail) {
        template.find('.answer-button').show()
        template.find('.question-option-button').hide()
    } else {
        template.find('.answer-button').hide()
        template.find('.question-option-button').show()
    }

    return template.html()
}

function questionFormCheck() {

    if ($('#question-textarea').val() === "") {

        alert("질문을 입력해주세요.")
        return false

    } else if ($('#question-form #category-code').val() === "") {
        alert("카테고리를 선택해주세요.")
        return false

    }
    
    return true
}

$('#category-select').change(function () {
    const categoryCode = $('#category-select option:selected').attr('value')
    $('#question-form #category-code').val(categoryCode)
})

$('body').on('click', '.my-answer', function () {
    alert('자신의 질문에는 답변할 수 없습니다.')
})

// 질문하기 버튼 클릭 시 카테고리 조회하여 모달창에 셋팅
$('#ask-question-button').click(function () {


    $.ajax({
        url: '/question/categories',
        type: 'GET',
        success: function (categories) {

            // 질문 모달창의 submit 버튼 교체
            $('#question-modal #add-question-button').show()
            $('#question-modal #edit-question-button').hide()

            // 카테고리 목록 초기화
            $('#category-select').html('<option selected hidden value="1">Choose category</option>').removeAttr('disabled')

            let cats = ''

            categories.forEach(function (cat) {

                cats += ('<option value="' + cat.categoryCode + '">' + cat.categoryName + '</option>')
            })

            // 카테고리 리스트에 카테고리 목록 추가
            $('#category-select').append(cats)
        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})

// 질문 Modal Window textarea 자동 높이 조절
$('#question-textarea').on('keyup', function () {

    $(this).css('height', 'auto')
    $(this).height($(this).prop('scrollHeight'))

    if ($(this).height() >= 130) {
        $(this).css('overflow', '')
    }

})

$('#question-modal').on('hidden.bs.modal', function () {
    $('#question-textarea').val('')
    $('#question-textarea').height('auto')

})

// 질문 등록 버튼 클릭
$('#add-question-button').click(function () {

    if (!questionFormCheck()) {
        return false
    }

    $('#question-form').submit()

})

// 질문 수정 버튼 클릭
$('#edit-question-button').click(function () {

    const quesNo = $('#question-form input#ques-no').val()
    const question = $('#question-form #question-textarea').val()
    const userEmail = $('#question-form input#user-email').val()

    $.ajax({
        type: 'POST',
        url: '/question/update',
        data: JSON.stringify({quesNo: quesNo, question: question, userEmail: userEmail}),
        contentType: 'application/json',
        dataType: 'json',
        context: this,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        success: function (question) {

            $('.question-text').html(question.question)

            $('#question-modal').modal('hide')

            alert('질문이 성공적으로 수정되었습니다.')
        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})

$('body').on('click', '.question-popover-item', function () {

    const quesNo = $(this).data('ques-no')
    const optionType = $(this).data('option-type')

    if (optionType === 'Delete') {

        if (!deleteConfirm()) {
            return
        }

        $.ajax({
            type: 'POST',
            url: '/question/delete',
            data: JSON.stringify({quesNo: quesNo}),
            contentType: 'application/json',
            // dataType: 'json',
            context: this,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token)
            },
            success: function () {

                // redirection

                window.location.href = "/"

                // // 댓글 포함된 comment-section
                // const commentSection = $('.comment[data-co-no="' + deletedCoNo + '"]').parents('.comment-section')
                //
                // // 해당 댓글 포함 하위 댓글 모두 화면에서 지우기
                // deleteCommentTree(deletedCoNo)
                //
                // // 첫번째 댓글 border-top 지우기
                // commentSection.find('.comment[data-co-level="1"]').first().removeClass('border-top-gray')
                //
                // // 해당 댓글의 부모 댓글의 자식 댓글 카운트 업데이트
                // countChildComments($(this).data('parent-co-no'))

            },
            error: function (error) {
                alert(error.responseText)
            }
        })
    }

    else if (optionType === 'Edit') {

        $.ajax({
            type: 'GET',
            url: '/question/select',
            data: {quesNo: quesNo},
            contentType: 'application/json',
            success: function (question) {

                // 질문 모달창의 submit 버튼 교체
                $('#question-modal #add-question-button').hide()
                $('#question-modal #edit-question-button').show()

                $('#question-form #ques-no').val(question.quesNo)
                $('#question-form #question-textarea').val(question.question)
                $('#category-select option').text(question.categoryName)
                $('#category-select').attr('disabled', 'disabled')

            },
            error: function (error) {
                alert(error.responseText)
            }
        })


    }
})
