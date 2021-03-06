// 댓글 popover option 초기화
function initAnswerPopover() {

    $('.answer-option-button').each(function (idx, element) {

        $(function() {
            $(element).popover({
                trigger: 'click',
                html: true,
                sanitize: false,
                content: $(element).find('.answer-option-popover-content').html()
            })
        })
    })
}

// answer 엘레멘트가 없을 경우 no content 이미지 보여주기
if (!isExist('.answer')) {
    $('#no-content').show()
}

function answerFormCheck() {

    if ($('#answer-textarea').val() === "") {
        alert("답변을 입력해주세요.")

        return false
    }

    return true
}

$('body').on('click', '.readMore-button', function () {
    $(this).parent().css('max-height', $(this).parent().prop('scrollHeight'))
    $(this).hide()
    $(this).parent().find('.overflow-fade').css('display', 'none')
})

// 답글 길이 초과 시 일부만 보여주는 함수
function fadePost() {
    $('.fade-post').each(function() {
        if (isOverflown($(this))) {

            $(this).find('.overflow-fade').removeClass('hidden')
            $(this).parent().find('.readMore-button').removeClass('hidden')
        }
    })
}


// 답변글 popover 옵션
$(document).on('click', '.answer-popover-item', function () {

    $('.answer-option-button').popover('hide')
    // 질문글 답변 갯수 체크 목적
    const quesNo = $(this).data('ques-no')

    const ansNo = $(this).data('ans-no')
    const optionType = $(this).data('option-type')


    if (optionType === 'Delete') {

        if (!deleteConfirm()) {
            return
        }

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

                const answer = $('.answer[data-ans-no="' + ansNo + '"]')
                // answer.closest('.pair').remove()
                answer.remove()

                $('#answer-count').html(parseInt($('#answer-count').html()) - 1)

                initAnswerPopover()

                // answer 엘레멘트가 없을 경우 no content 이미지 보여주기
                if (!isExist('.answer')) $('#no-content').show()


            },
            error: function (error) {
                alert(error.responseText)
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
            error: function (error) {
                alert(error.responseText)
            }
        })
    }
})

// 답변 버튼 클릭 시 답변 modal에 유저정보, 질문글 띄우기
$('body').on('click', '.answer-button', function() {

    const quesNo = $(this).data('ques-no')

    $.ajax({
        url: '/answer/checkAnswered',
        type: 'GET',
        data: {quesNo: quesNo},
        dataType: 'json',
        contentType: 'application/json',
        success: function (answered) {

            if (answered) {
                alert('이미 답변 하였습니다.')

            } else {

                $.ajax({
                    url: '/user/get',
                    type: 'GET',
                    dataType: 'json',
                    contentType: 'application/json',
                    context: this,
                    success: function (user) {

                        // 답변 모달창의 submit 버튼 교체
                        $('#answer-modal #add-answer-button').show()
                        $('#answer-modal #edit-answer-button').hide()

                        $('#answer-modal #user-img').attr('src', '/uploadedImages' + user.photo)
                        $('#answer-modal #user-name').html(user.name)
                        $('#answer-modal #user-occupation').html(user.occupation)
                        $('#answer-modal #asked-question').html($('.question[data-ques-no="' + quesNo + '"]').find('.question-text').text())
                        $('#answer-modal #ques-no').val(quesNo)

                        $('#answer-modal').modal('show')

                    }
                })
            }
        },
        error: function (error) {
            alert(error.responseText)
        }
    })
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

    if (!answerFormCheck()) {
        return false
    }
    // text editor 컨텐트에서 inserted-image src 가져오기
    let editorContent = tinymce.activeEditor.getContent()

    $('#answer-images').append($.parseHTML(editorContent))
    let imagePaths = []
    $('#answer-images .inserted-image').each(function (idx, element) {
        imagePaths.push($(element).attr('src'))
    })
    $('#answer-images').html('')

    const answer = $('#answer-textarea').val()
    const quesNo = $('#answer-form #ques-no').val()

    $.ajax({
        url: '/answer/create',
        type: 'POST',
        data: JSON.stringify({answer: answer, quesNo: quesNo, imagePath: imagePaths}),
        contentType: 'application/json',
        context: this,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        success: function (answer) {

            // if ($(location).attr('pathname').indexOf('questions') > 0) {
            location.reload()
            //     return
            // }

            // let template = $('#answer-template')
            //
            // template.find('.comment-form').attr('data-ans-no', answer.ansNo)
            // template.find('.answer').attr('data-ans-no', answer.ansNo)
            // template.find('.user-profile-link').attr('href', '/user/profile/' + answer.userEmail)
            // template.find('.answer-user-name').prepend(answer.userName)
            // template.find('.answer-user-occupation').prepend(answer.userOccupation)
            // template.find('.answer-reg-date').prepend(answer.regDate)
            // template.find('.answer-text').prepend(answer.answer)
            // template.find('.answer-user-photo').attr('src', '/uploadedImages' + answer.userPhoto)
            // template.find('.footer-container').attr('data-ans-no', answer.ansNo)
            //
            // if (answer.commentCnt > 0) {
            //     template.find('.comment-count').html(answer.commentCnt)
            // }
            //
            // if (answer.likes > 0) {
            //     addedAnswer.find('.answer-like-cnt').prepend(answer.likes)
            // }
            // if (answer.dislikes > 0) {
            //     addedAnswer.find('.answer-dislike-cnt').prepend(answer.dislikes)
            // }
            //
            // template.find('.answer-popover-item').attr('data-ans-no', answer.ansNo)
            // template.find('.answer-popover-item').attr('data-ques-no', answer.quesNo)
            //
            // $('#answer-list').prepend(template.html())
            //
            // $('#answer-count').html(parseInt($('#answer-count').html()) + 1)
            //
            // // answer 엘레멘트가 없을 경우 no content 이미지 보여주기
            // if (isExist('.answer')) $('#no-content').hide()
            //
            // initAnswerPopover()
            //
            // $('.inserted-image').ready(function () {
            //     fadePost()
            // })
            //
            // // 모달창 종료
            // $('#answer-modal').modal('toggle')

        },
        error: function (error) {
            alert(error.responseText)
        }
    })


})

// 답변 수정
$('#edit-answer-button').click(function () {
    
    // text editor 컨텐트에서 inserted-image src 가져오기
    let editorContent = tinymce.activeEditor.getContent()
    $('#answer-images').append($.parseHTML(editorContent))
    let imagePaths = []
    $('#answer-images .inserted-image').each(function (idx, element) {
        imagePaths.push($(element).attr('src'))
    })
    $('#answer-images').html('')

    const answer = $('#answer-textarea').val()
    const quesNo = $('#answer-form #ques-no').val()
    const ansNo = $('#answer-form #ans-no').val()

    $.ajax({
        url: '/answer/update',
        type: 'POST',
        data: JSON.stringify({ansNo: ansNo, answer: answer, quesNo: quesNo, imagePath: imagePaths}),
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        contentType: 'application/json',
        success: function (answer) {

            // 등록된 답변을 답변 리스트에 추가
            $('.answer[data-ans-no="' + answer.ansNo + '"]').find('.answer-text').html(answer.answer)

            $('#answer-modal').modal('toggle')

            initAnswerPopover()

        },
        error: function (error) {
            alert(error.responseText)
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
                tags = tags + '<div class="d-flex" style="width: 100%;"><img class="inserted-image px-auto" alt="photo" src= "/uploadedImages' + path + '" style="width: 50%;"/></div></b>'
            }

            tinymce.activeEditor.insertContent(tags)

            $('#image').val('')

            // 사진 클릭 시 태그 삭제후에 x 버튼으로 대신할 것
            // $("#content_ifr").contents().find(".inserted-image").bind('click', function () {
            //     this.remove()
            // })

        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})


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

// 최초 조회된 답변 popover
initAnswerPopover()

// 길이 초과 답변 부분 숨기기
$('.inserted-image').ready(function () {
    fadePost()
})