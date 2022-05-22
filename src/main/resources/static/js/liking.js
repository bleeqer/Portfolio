$('.like-button').click(function () {

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        dataType: 'json',
        data: {
            ansNo: $(this).data('ans-no')
        },
        context: this,
        success: function (result) {

            if (result.likes > 0) {

                $(this).find('.like-cnt').html(result.likes)

            } else {

                $(this).find('.like-cnt').html('')
            }

            if (result.dislikes > 0) {

                $(this).find('.dislike-cnt').html(result.dislikes)

            } else {

                $(this).find('.dislike-cnt').html('')
            }

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
        data: {
            ansNo: $(this).data('ans-no')
        },
        context: this,
        success: function (likeCnt) {

            if (likeCnt > 0) {

                $(this).find('.like-cnt').html(likeCnt)
            } else {

                $(this).find('.like-cnt').html('')
            }

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }

    })
})



