// 댓글 popover option 초기화
function initAnswerPopover() {
    $(function() {
        $('.answer-option-button').popover({
            trigger: 'focus',
            html: true,
            sanitize: false,
            content: $(this).find($('.answer-option-popover-content')).html()
        })
    })
}

// 최초 조회된 답변 popover
initAnswerPopover()

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

// 답변 버튼 클릭 시 답변 modal에 유저정보, 질문글 띄우기
$('.answer-button').click(function () {

    $.ajax({
        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        context: this,
        success: function (user) {
            $('#answer-modal').find('#user-img').attr('src', user.photo)
            $('#answer-modal').find('#user-name').html(user.name)
            $('#answer-modal').find('#user-occupation').html(user.occupation)
            $('#answer-modal').find('#asked-question').html($('.question[data-ques-no="' + $(this).data('ques-no') + '"]').find('.question-text').text())
            $('#answer-modal').find('#ques-no').val($(this).data('ques-no'))

        }
    })
})

function isOverflown(element) {
    return element.prop('scrollHeight') > element.height()
}

// 질문 Modal Window textarea 자동 높이 조절
$('#question-textarea').on('keyup', function () {
    $(this).height('26')
    let scHeight = $(this).prop('scrollHeight')
    $(this).height(scHeight)
})

// 답변 Modal Window open 시
$('#answer-modal').on('shown.bs.modal', function () {
    initEditor()
})

$('#answer-modal').on('hidden.bs.modal', function () {
    tinymce.activeEditor.destroy()
    $('#answer-textarea').val('')
})

$('#add-answer-button').click(function () {

    alert($('#answer-form').serialize())

    $.ajax({
        url: '/answer/create',
        type: 'POST',
        data: $('#answer-form').serialize(),
        success: function (answer) {

            // 등록된 답변을 답변 리스트에 추가
            $('#answer-list').prepend(answer)

            // 모달창 종료
            $('#answer-modal').modal('toggle')
        }
    })

})

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
            xhr.setRequestHeader('X-CSRF-TOKEN', $('input[name="_csrf"]').val())
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

