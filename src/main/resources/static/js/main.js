// 템플릿에 전달받은 댓글 데이터 세팅 후 화면에 삽입
function addPair (pair) {

    let template = $('#pair-template')

    // pair data 속성 셋팅
    template.find('.pair').attr('data-ques-no', pair.quesNo)

    // pair 셋팅
    template.find('.question-text').html(pair.question)
    template.find('.answer-text p').html(pair.answer)
    template.find('.answer-user-photo').attr('src', '/uploadedImages' + pair.userPhoto)
    template.find('.user-profile-link').attr('src', '/user/profile/' + pair.userEmail)
    template.find('.answer-user-name').html(pair.userName)
    template.find('.answer-user-occupation').html(pair.userOccupation)
    template.find('.answer-reg-date').html(pair.answerRegDate)
    template.find('.question-link').attr('href', '/question/' + pair.quesNo)

    // if ($('#logged-in-user').val() !== question.userEmail) {
    //     template.find('.answer-button').show()
    //     template.find('.question-option-button').hide()
    // } else {
    //     template.find('.answer-button').hide()
    //     template.find('.question-option-button').show()
    // }

    return template.html()
}

$('.input-focus').focus(function () {
    $(this).parents('.input-container').addClass('border-blue')
    $(this).parents('.input-container').css('min-width', 300)
})

$('.input-focus').focusout(function () {
    $(this).parents('.input-container').removeClass('border-blue')
    $(this).parents('.input-container').css('min-width', 150)
})

$('body').on('click', '.login-first', function () {
    result = confirm('로그인 화면으로 이동하시겠어요? (임시 계정이 제공 됩니다.)')

    if (result) {
        window.location.href = '/user/login'
    }
})

// 질문글 url 복사
$(document).on('click', '.share-button', function () {
    const url = window.location.origin + '/question/' + $(this).data('ques-no')
    navigator.clipboard.writeText(url)

    alert('url이 성공적으로 복사 되었습니다.\n' + url)
})

// 숨기기 버튼 클릭
$(document).on('click', '.hide-button', function () {

    $('.hide-object[data-ques-no="' + $(this).closest('.pair').data('ques-no') + '"]').hide()

})


$('[data-toggle="description"]').each(function (idx, element) {
    $(function(){
        $(element).popover({
            trigger: 'hover',
            html: true,
            sanitize: false,
            content: '<span class="py-5 px-2" style="line-height: 2.5;">' + $(element).data('content') + '</span>',
            placement: 'bottom'
        })
    })
})

function isExist(selector) {

    return $(selector).length-1 > 0
}