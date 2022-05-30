
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
        content_style: "body { margin: 0; color: white !important; }",
        content_css: "/static/css/tinymceCustom.css",
        browser_spellcheck: true,
        // height: $("#modal-question").height() - $('title').height(),
        setup: function (editor) {
            // editor.getBody().style.backgroundColor = '#E5FFCC';
            // editor.contentDocument.body.style.backgroundColor = '#D6FFD6';
            // editor.activeEditor.style.backgroundColor = 'black'
            editor.on('change', function () {
                editor.save()
            })
        },
        relative_urls: false,
    })
}

