$('.comment-button').click(function () {

    $('.comment-section[data-ans-no="' + $(this).data('ans-no') + '"]').toggle()
})