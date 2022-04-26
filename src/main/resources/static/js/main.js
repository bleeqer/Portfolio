
$('.best-title:last-child').removeClass('border-bottom-grey')

$('.input-focus').focus(function () {
    $(this).parents('.input-container').addClass('border-blue')
})

$('.input-focus').focusout(function () {
    $(this).parents('.input-container').removeClass('border-blue')
})