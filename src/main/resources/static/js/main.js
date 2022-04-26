
$('.best-title:last-child').removeClass('border-bottom-grey')

$('.input-focus').focus(function () {
    $(this).parent('div').addClass('border-blue')
    // $(this).parent('div').addClass('border-blue')
})

$('.input-focus').focusout(function () {
    $(this).parent('div').removeClass('border-blue')
    // $(this).parent('div').addClass('border-blue')
})