// 댓글 popover option 초기화
function initAnswerPopover() {

    $('.answer-option-button').each(function (idx, element) {

        $(function() {
            $(element).popover({
                trigger: 'focus',
                html: true,
                sanitize: false,
                content: $(element).find('.answer-option-popover-content').html()
            })
        })
    })

}

// 최초 조회된 답변 popover
initAnswerPopover()

// 엘레멘트 존재 여부 체커
function isExist(selector) {
    return $(selector).length > 0

}

$('.readMore-button').on('click', function() {
    $(this).parent().find('.fade-post').css('max-height', $(this).parent().find('.fade-post').prop('scrollHeight') + 'px')
    $(this).hide()
    $(this).parent().find('.overflow-fade').css('display', 'none')
})

$('.fade-post').each(function() {

    if (isOverflown($(this))) {
        $(this).find('.overflow-fade').removeClass('hidden')
        $(this).parent().find('.readMore-button').removeClass('hidden')
    }
})

// 답변글 popover 옵션
$(document).on('click', '.answer-popover-item', function () {

    // 질문글 답변 갯수 체크 목적
    const quesNo = $(this).data('ques-no')

    const ansNo = $(this).data('ans-no')
    const optionType = $(this).data('option-type')

    if (optionType === 'Delete') {

        $.ajax({
            type: 'POST',
            url: '/answer/delete',
            data: JSON.stringify({ansNo: ansNo, quesNo: quesNo}),
            contentType: 'application/json',
            context: this,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token)
            },
            success: function (ansNo) {

                $('.answer[data-ans-no="' + ansNo + '"]').hide()
                initAnswerPopover()
                
                // 보여지는 answer 엘레멘트가 없을 경우 no content 이미지 보여주기
                if (!isExist('.answer:visible')) $('#no-content').show()

            },
            error: function () {
                alert('오류가 발생했습니다.')
            }
        })
    }

    else if (optionType === 'Edit') {

        $.ajax({
            type: 'GET',
            url: '/answer/select',
            data: {ansNo: ansNo},
            dataType: 'json',
            contentType: 'application/json',
            success: function (answer) {

                // 답변 모달창의 submit 버튼 교체
                $('#answer-modal #add-answer-button').hide()
                $('#answer-modal #edit-answer-button').show()

                $('#answer-modal #asked-question').html($('.question-text').text())
                $('#answer-modal #user-img').attr('src', answer.userPhoto)
                $('#answer-modal #user-name').text(answer.userName)
                $('#answer-modal #user-occupation').text(answer.userOccupation)
                $('#answer-modal #answer-textarea').val(answer.answer)
                $('#answer-modal #ans-no').val(answer.ansNo)
                $('#answer-modal #ques-no').val(answer.quesNo)



            },
            error: function () {
                alert('오류가 발생했습니다.')
            }
        })


    }
})

// 답변 버튼 클릭 시 답변 modal에 유저정보, 질문글 띄우기
$('.answer-button').click(function () {

    $.ajax({
        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        context: this,
        success: function (user) {
            $('#answer-modal #asked-question').html($('.question-text').text())
            $('#answer-modal #user-img').attr('src', user.photo)
            $('#answer-modal #user-name').html(user.name)
            $('#answer-modal #user-occupation').html(user.occupation)
            $('#answer-modal #asked-question').html($('.question[data-ques-no="' + $(this).data('ques-no') + '"]').find('.question-text').text())
            $('#answer-modal #ques-no').val($(this).data('ques-no'))

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
    $('#question-textarea').height($(this).height($(this).prop('scrollHeight')))

})



// 답변 Modal Window open 시 text editor 초기화
$('#answer-modal').on('shown.bs.modal', function () {
    initEditor()
})

// 답변 Modal Window close 시 text editor 종료 및 텍스트 input 초기화
$('#answer-modal').on('hidden.bs.modal', function () {
    tinymce.activeEditor.destroy()
    $('#answer-textarea').val('')
})

// 답변 등록
$('#add-answer-button').click(function () {

    $.ajax({
        url: '/answer/create',
        type: 'POST',
        data: $('#answer-form').serialize(),
        context: this,
        success: function (answer) {

            const currentURL = window.location.pathname.split('/')[1]

            if (currentURL === 'question') {

                // 등록된 답변을 답변 리스트에 추가
                $('#answer-list').prepend(answer)
                initAnswerPopover()

                // 답변글 존재여부 체크 후 no content 이미지 숨기기
                if (isExist('.answer')) {
                    $('#no-content').hide()
                }

            } else if (currentURL === 'questions') {
                
                // 답변 등록된 질문글 숨기기
                $('.question[data-ques-no="' + $('#ques-no').val() + '"]').hide()
                alert('답변이 성공적으로 등록되었습니다.')
            }

            // 모달창 종료
            $('#answer-modal').modal('toggle')

        }
    })
})

// 답변 수정
$('#edit-answer-button').click(function () {

    $.ajax({
        url: '/answer/edit',
        type: 'POST',
        data: $('#answer-form').serialize(),
        success: function (answer) {

            // 등록된 답변을 답변 리스트에 추가
            $('.answer[data-ans-no="' + answer.ansNo + '"]').find('.answer-text').html(answer.answer)

            $('#answer-modal').modal('toggle')

            initAnswerPopover()

        }
    })
})

// 이미지 업로드 버튼 클릭 시 file input trigger
$('#image-upload-button').click(function () {
    $('#image').trigger('click')
})

// 이미지 업로드 함수. image input 변경 시 실행됨
$('#image').on("change", function () {

    // input 태그의 파일 데이터
    const files = this.files

    // ajax submit용 form data
    const formData = new FormData()

    // input 태그 파일을 데이터 순회하며 form data에 추가
    // 첫번째 스트링 인자('image')는 서버에서 multipart file의 파라미터명으로 쓰이므로 주의
    for (let i=0; i<files.length; i++) {

        formData.append('image', files[i])
    }

    // ajax 호출
    $.ajax({
        // 요청 URL
        url: "/file/upload",

        // 파일 전송 시
        enctype: "multipart/form-data",

        // data의 스트링화(stringify) 방지
        processData: false,

        // contentType의 default 값 false
        contentType: false,

        // 전송할 데이터
        data: formData,

        // 데이터 전송 방식
        type: 'POST',

        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },

        // ajax 요청 성공 시 콜백함수
        success: function (paths) {

            let tags = ''

            for (const path of paths) {

                // 이미지 태그 클릭하면 에러 발생함 나중에 삭제버튼 추가하기
                tags = tags + '<div class="d-flex" style="width: 100%;"><img class="inserted-image px-auto" alt="photo" src= "' + path + '" style="width: 80%;"/></div></b>'
            }

            tinymce.activeEditor.insertContent(tags)

            // 사진 클릭 시 태그 삭제후에 x 버튼으로 대신할 것
            // $("#content_ifr").contents().find(".inserted-image").bind('click', function () {
            //     this.remove()
            // })

        },
        error: function () {
            alert("파일 유형을 확인해주세요.")
        }
    })
})



// 질문 Modal Window close 시
// $('#question-modal').on('hidden.bs.modal', function () {
//
//     // form 내용 초기화
//     $(this).find('form').trigger('reset')
//
//     // 카테고리 초기화
//     $('#first-category').val('1')
//     $('#second-category').val('1')
// })

// // add question button 클릭 시 form submit
// $('#add-question-button').click(function () {
//     $('#question-form')
// })

// text editor 초기화 함수
async function initEditor () {

    await tinymce.init({
        selector: '#answer-textarea',
        menubar: false,
        statusbar: false,
        toolbar: false,
        auto_focus: true,
        content_css: "/static/css/tinymceCustom.css",
        browser_spellcheck: true,
        height : "260",
        relative_urls: false,
        setup: function (editor) {
            editor.on('change', function () {
                editor.save()
            })
        },
    })
}

