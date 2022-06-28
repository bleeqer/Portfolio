

$('.best-title:last-child').removeClass('border-bottom-grey')

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