

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

