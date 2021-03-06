// like 여부 체크 후 하이라이트
function answerLikeHighlight(selector) {
    
    // 마지막 element는 템플릿이므로 제외
    $(selector).slice(0, -1).each(function (idx, element) {
        $.ajax({
            url: '/answer/checkLiked',
            type: 'GET',
            async: false,
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
            },
            error: function (error) {
                console.log(error.responseText)

                // alert(error.responseText)
            }
        })
    })
}

function commentLikeHighlight() {

    $('.comment').slice(0, -1).each(function (idx, element) {
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
            },
            error: function (error) {
                alert(error.responseText)
            }
        })
    })
}


// answerLikeHighlight('.pair')
answerLikeHighlight('.answer')

$('body').on('click', '.answer-like-button', function () {

    const answer = $(this).closest('.footer-container')
    const ansNo = answer.data('ans-no')

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        data: {ansNo: ansNo},
        context: this,
        success: function (result) {

            if (result.likes <= 0) {
                result.likes = ' '
            }

            if (result.dislikes <= 0) {
                result.dislikes = ' '
            }

            // answerLikeHighlight('.pair')
            answerLikeHighlight('.answer')
            answer.find('.answer-like-cnt').html(result.likes)
            answer.find('.answer-dislike-cnt').html(result.dislikes)


        },
        error: function (error) {
            alert(error.responseText)
        }

    })
})

$('body').on('click', '.answer-dislike-button', function () {

    const answer = $(this).closest('.footer-container')
    const ansNo = answer.data('ans-no')

    $.ajax({
        url: '/answer/dislike',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        data: {ansNo: ansNo},
        context: this,
        success: function (result) {

            if (result.likes <= 0) {
                result.likes = ''
            }

            if (result.dislikes <= 0) {
                result.dislikes = ''
            }

            answerLikeHighlight('.pair')
            answerLikeHighlight('.answer')
            answer.find('.answer-like-cnt').html(result.likes)
            answer.find('.answer-dislike-cnt').html(result.dislikes)



        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})

$('body').on('click', '.comment-like-button', function () {

    const comment = $(this).closest('.comment')
    const coNo = comment.data('co-no')

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

            comment.find('.comment-like-cnt').html(result.likes)
            comment.find('.comment-dislike-cnt').html(result.dislikes)

        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})

$('body').on('click', '.comment-dislike-button', function () {

    const comment = $(this).closest('.comment')
    const coNo = comment.data('co-no')

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

            comment.find('.comment-like-cnt').html(result.likes)
            comment.find('.comment-dislike-cnt').html(result.dislikes)

        },
        error: function (error) {
            alert(error.responseText)
        }
    })
})



