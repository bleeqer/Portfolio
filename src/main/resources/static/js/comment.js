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
            },
            error: function () {
                console.log('error occurred')
            }
        })
    }
})

$('.comment-section').on('click', 'div.reply-button', function () {

                            const parentNo = $(this).parents('.comment').data('co-no')

                            $('.comment[data-parent-co-no="' + parentNo + '"]').toggle()
                            $('.comment[data-co-no="' + parentNo + '"] .reply-input-container').toggle()
                        })
                     // .on

$('.comment-section').on('click', '.comment-submit-button', function () {
    alert('clicked')
    const formData = new FormData($(this).parent().find('.comment-form')[0])

    $.ajax({
        url: '/comment/create',
        type: 'POST',
        contentType: false,
        processData: false,
        data : formData,
        beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            alert('댓글 등록 성공')

        },
        error: function () {
            console.log('error occurred')
        }
    })
})