$('.search-input').on('keyup', (function () {
    const keyword = $(this).val()
    const keywordDisplay = $('.display-keyword')

    keywordDisplay.html(keyword)

    if (keyword.length > 0) {
        $('.search-display').removeClass('hidden')

    } else {
        $('.search-display').addClass('hidden')
    }
}))

$('.search-input').focus(function () {
    $('.search-overlay').addClass('show-search-overlay')


})

$('.search-input').focusout(function () {
    $('.search-overlay').removeClass('show-search-overlay')
})

$('.search-button').click(function () {
    $('#search-form').submit()
})
