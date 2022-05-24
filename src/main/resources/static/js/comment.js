
function getComments(data) {

    let comments

    $.ajax({
        url: '/comment/',
        type: 'GET',
        contentType: 'application/json',
        async: false,
        data : data,
        success: function (result) {
            comments = result
        },
        error: function () {
            console.log('오류가 발생했습니다.')
        }
    })

    return comments
}

// 댓글
$('.comment-button').click(function () {

    const ansNo = $(this).data('ans-no')

    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')

    commentSection.toggle()

    if (commentSection.css('display') === 'block') {

        const data = {ansNo: ansNo}

        commentSection.find('.comment-list').html(getComments(data))

    }
})

// 대댓글
$('.comment-section').on('click', 'div.reply-button', function () {

                            const parentNo = $(this).parents('.comment').data('co-no')

                            $('.comment[data-parent-co-no="' + parentNo + '"]').toggle()
                            $('.comment[data-co-no="' + parentNo + '"] .reply-input-container').toggle()
                        })

$('.view-more-comments').click(function () {

    const ansNo = $(this).data('ans-no')


    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')

    const data = {ansNo: ansNo,
                  lastCoNo: commentSection.find('.comment[data-co-level="1"]').last().data('co-no')}

    commentSection.find('.comment-list').append(getComments(data))


})


$('.comment-section').on('click', '.comment-submit-button', function () {

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