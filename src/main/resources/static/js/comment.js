// 댓글 가져오기
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

// root 댓글 및 하위댓글 모두 지우기
function deleteCommentTree (rootCoNo) {

    // 해당 댓글 지우기
    $('.comment[data-co-no="' + rootCoNo + '"]').remove()


    // 해당 댓글을 부모 댓글로 가지는 댓글 지우기
    if (typeof rootCoNo !== 'undefined') {
        deleteCommentTree($('.comment[data-parent-co-no="' + rootCoNo + '"]').data('co-no'))
    }
}

// 답변글의 댓글 보기
$('.comment-button').click(function () {

    const ansNo = $(this).data('ans-no')

    alert(ansNo)

    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')

    commentSection.toggle()

    if (commentSection.css('display') === 'block') {

        const data = {ansNo: ansNo}

        commentSection.find('.comment-list').html(getComments(data))

        commentSection.find('.comment').first().removeClass('border-top-gray')

        // 마지막 댓글일 경우 댓글 더보기 버튼 숨기기
        if (commentSection.find('.last-checker').last().data('is-last') === "Y") {

            $(this).find('.view-more-comments').addClass('hidden')
        }

        // popover 초기화
        $(function () {
            $('.comment-option-button').popover({
                html: true,
                sanitize: false,
                content: function() {
                    return $(this).find('.option-popover-content').html()
                }
            })
        })

    }
})

$(document).on('click', '.popover-item.option', function () {
    const coNo = $(this).data('co-no')
    const ansNo = $(this).data('ans-no')
    const optionType = $(this).data('option-type')
    
    if (optionType === 'Delete') {
        
        $.ajax({
            type: 'GET',
            url: '/comment/delete',
            data: {coNo: coNo, ansNo: ansNo},
            contentType: 'application/json',
            context: this,
            success: function (deletedCoNo) {

                // 댓글 포함된 comment-section
                const commentSection = $('.comment[data-co-no="' + deletedCoNo + '"]').parents('.comment-section')

                // 해당 댓글 포함 하위 댓글 모두 화면에서 지우기
                deleteCommentTree(deletedCoNo)

                // 삭제 댓글이 첫번째 댓글일 수 있으므로 댓글이 포함되어 있었던 comment-section의 첫번째 댓글 top border 지우기
                commentSection.find('.comment').first().removeClass('border-top-gray')
                
                // popover 숨기기
                $(this).parents('.popover').popover('hide')

            },
            error: function () {
                alert('오류가 발생했습니다.')
            }
        })
    }
})

// 대댓글 보기
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

    // 마지막 댓글일 경우 댓글 더보기 버튼 숨기기
    if (commentSection.find('.last-checker').last().data('is-last') === "Y") {

        $(this).addClass('hidden')
    }


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

