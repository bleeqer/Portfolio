

$('.best-title:last-child').removeClass('border-bottom-grey')

$('.input-focus').focus(function () {
    $(this).parents('.input-container').addClass('border-blue')
    $(this).parents('.input-container').css('min-width', 300)
})

$('.input-focus').focusout(function () {
    $(this).parents('.input-container').removeClass('border-blue')
    $(this).parents('.input-container').css('min-width', 150)
})

$('.best-title').mouseenter(function () {
        $(this).css('height', $(this).prop('scrollHeight'))
    })
    .mouseleave(function () {
        $(this).css('height', 32)
    })

// 질문글 url 복사
$(document).on('click', '.share-button', function () {

    const url = window.location.origin + '/question/' + $(this).data('ques-no')
    navigator.clipboard.writeText(url)

    alert('url이 성공적으로 복사 되었습니다.\n' + url)
})

// 숨기기 버튼 클릭
$('body').on('click', '.hide-button', function () {
    alert($(this).data('ques-no'))
    $('.hide-object[data-ques-no="' + $(this).data('ques-no') + '"]').hide()
})