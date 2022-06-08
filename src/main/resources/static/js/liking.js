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

            highlightIfLiked('.pair')

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

            highlightIfLiked('.pair')

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

            $('.comment-like-cnt[data-co-no="' + coNo + '"]').html(result.likes)

            $('.comment-dislike-cnt[data-co-no="' + coNo + '"]').html(result.dislikes)

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }
    })
})



