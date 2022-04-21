$('.readMore-button').on('click', function() {
    $(this).parent().find('.fade-post').css('overflow', 'visible')
    $(this).parent().find('.fade-post').css('max-height', '100%')
    $(this).hide()
    $(this).parent().find('.overflow-fade').css('display', 'none')
})

$('.fade-post').each(function() {
    if (isOverflown($('.fade-post'))) {
        $(this).find('.overflow-fade').css('display', 'block')
    }
})

$('.best-title:last-child').removeClass('border-bottom-grey')
