$('.like-button').click(function () {

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        dataType: 'json',
        data: {
            ansNo: $(this).parents('.footer-container').data('ans-no')
        },
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            $(this).parents('.like-container').find('.like-cnt').html(result.likes)

            $(this).parents('.like-container').find('.dislike-cnt').html(result.dislikes)


        },
        error: function () {
            alert('오류가 발생했습니다.')
        }

    })
})

$('.dislike-button').click(function () {

    $.ajax({
        url: '/answer/dislike',
        type: 'GET',
        dataType: 'json',
        data: {
            ansNo: $(this).parents('.footer-container').data('ans-no')
        },
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            $(this).parents('.like-container').find('.like-cnt').html(result.likes)

            $(this).parents('.like-container').find('.dislike-cnt').html(result.dislikes)

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }
    })
})



