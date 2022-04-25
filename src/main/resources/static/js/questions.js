
$('.unanswered-question:last-child').removeClass('border-bottom-grey')

$('.readMore-button').on('click', function() {
    $(this).parent().find('.fade-post').css('max-height', '100%')
    $(this).parent().find('.fade-post').css('height', $(this).parent().find('.fade-post').prop('scrollHeight'))
    $(this).hide()
    $(this).parent().find('.overflow-fade').css('display', 'none')
})

$('.fade-post').each(function() {

    if (isOverflown($('.fade-post'))) {
        $(this).find('.overflow-fade').css('display', 'block')
        $(this).parent().find('.readMore-button').removeClass('hidden')
        $(this).css('height', '80')
    } else {
        $(this).css('height', 'scrollHeight')
    }

})