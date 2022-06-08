// like 여부 체크 후 하이라이트
function answerLikeHighlight(selector) {

    $(selector).each(function (idx, element) {
        $.ajax({
            url: '/answer/checkLiked',
            type: 'GET',
            data: {ansNo: $(element).data('ans-no')},
            dataType: 'json',
            contentType: 'application/json',
            success: function (like) {

                if (like.likeType === 'UP') {

                    $(element).find('.answer-like-button').addClass('bg-whiten')
                    $(element).find('.answer-dislike-button').removeClass('bg-whiten')

                } else if (like.likeType === 'DOWN') {

                    $(element).find('.answer-dislike-button').addClass('bg-whiten')
                    $(element).find('.answer-like-button').removeClass('bg-whiten')


                } else if (like.likeType === 'None') {

                    $(element).find('.answer-dislike-button').removeClass('bg-whiten')
                    $(element).find('.answer-like-button').removeClass('bg-whiten')

                }
            }
        })
    })
}

function commentLikeHighlight() {

    $('.comment').each(function (idx, element) {
        $.ajax({
            url: '/comment/checkLiked',
            type: 'GET',
            data: {coNo: $(element).data('co-no')},
            dataType: 'json',
            contentType: 'application/json',
            success: function (like) {

                if (like.likeType === 'UP') {

                    $(element).find('.comment-like-button').addClass('bg-whiten')
                    $(element).find('.comment-dislike-button').removeClass('bg-whiten')

                } else if (like.likeType === 'DOWN') {

                    $(element).find('.comment-dislike-button').addClass('bg-whiten')
                    $(element).find('.comment-like-button').removeClass('bg-whiten')


                } else if (like.likeType === 'None') {

                    $(element).find('.comment-dislike-button').removeClass('bg-whiten')
                    $(element).find('.comment-like-button').removeClass('bg-whiten')

                }
            }
        })
    })
}


answerLikeHighlight('.pair')
answerLikeHighlight('.answer')

$('body').on('click', '.answer-like-button', function () {

    const ansNo = $(this).data('ans-no')

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        dataType: 'json',
        data: {
            ansNo: ansNo
        },
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            answerLikeHighlight('.pair')
            answerLikeHighlight('.answer')

            $('.answer-like-cnt[data-ans-no="' + ansNo + '"]').html(result.likes)

            $('.answer-dislike-cnt[data-ans-no="' + ansNo + '"]').html(result.dislikes)

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }

    })
})

$('body').on('click', '.answer-dislike-button', function () {

    const ansNo = $(this).data('ans-no')

    $.ajax({
        url: '/answer/dislike',
        type: 'GET',
        dataType: 'json',
        data: {
            ansNo: ansNo
        },
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            answerLikeHighlight('.pair')
            answerLikeHighlight('.answer')

            $('.answer-like-cnt[data-ans-no="' + ansNo + '"]').html(result.likes)

            $('.answer-dislike-cnt[data-ans-no="' + ansNo + '"]').html(result.dislikes)

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }
    })
})

$('body').on('click', '.comment-like-button', function () {

    const coNo = $(this).data('co-no')

    $.ajax({
        url: '/comment/like',
        type: 'GET',
        dataType: 'json',
        data: {coNo: coNo},
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            commentLikeHighlight()

            $('.comment-like-cnt[data-co-no="' + coNo + '"]').html(result.likes)

            $('.comment-dislike-cnt[data-co-no="' + coNo + '"]').html(result.dislikes)


        },
        error: function () {
            alert('오류가 발생했습니다.')
        }

    })
})

$('body').on('click', '.comment-dislike-button', function () {

    const coNo = $(this).data('co-no')

    $.ajax({
        url: '/comment/dislike',
        type: 'GET',
        dataType: 'json',
        data: {
            coNo: coNo
        },
        context: this,
        success: function (result) {

            if (result.likes === 0) {
                result.likes = ''
            }

            if (result.dislikes === 0) {
                result.dislikes = ''
            }

            commentLikeHighlight()

            $('.comment-like-cnt[data-co-no="' + coNo + '"]').html(result.likes)

            $('.comment-dislike-cnt[data-co-no="' + coNo + '"]').html(result.dislikes)

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }
    })
})



