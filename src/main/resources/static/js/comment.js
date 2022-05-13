$('.comment-button').click(function () {

    const ansNo = $(this).data('ans-no')

    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')
    commentSection.toggle()

    const lastCommentNo = commentSection.find('.comment').last().data('co-no')
    if (commentSection.css('display') === 'block') {
        $.ajax({
            url: '/comment/' + ansNo,
            type: 'GET',
            contentType: 'application/json',
            data : {'lastCommentNo': lastCommentNo},
            success: function (result) {
                commentSection.find('.comment-list').html(result)
                alert(result)

            },
            error: function () {
                console.log('error occurred')
            }
        })
    }
})

$('.comment-section').on('click', 'div.reply-button', function () {

    const parentNo = $(this).parents('.comment').data('co-no')

    alert(parentNo)

    $('.comment[data-parent-co-no="' + parentNo + '"]').toggle()
})