
$('.best-title:last-child').removeClass('border-bottom-grey')

$('.input-focus').focus(function () {
    $(this).parents('.input-container').addClass('border-blue')
})

$('.input-focus').focusout(function () {
    $(this).parents('.input-container').removeClass('border-blue')
})

// navbar 버튼 클릭 시 버튼 활성화
$('a').each(function () {
    const currentURL = $(location).attr('pathname')

    if ($(this).attr('href') === currentURL) {
        $(this).find('path').addClass('svg-fill')
    }
})
