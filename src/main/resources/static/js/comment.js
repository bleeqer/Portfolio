// 댓글 popover option 초기화
function initPopovers() {
    $('.comment-option-button').popover({
        html: true,
        sanitize: false,
        content: function() {
            return $(this).find('.option-popover-content').html()
        }
    })
}


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

// child comments 갯수 세기

function countChildComments(coNo) {

    // 자식 댓글들
    const childComments = $('.comment[data-parent-co-no="' + coNo + '"]')

    // 부모 댓글 댓글수 엘레멘트에 자식 댓글 갯수 넣기
    $('.comment[data-co-no="' + coNo + '"] .comment-count').html(childComments.length)

}

function recursiveCountChildComments(coNo) {

    // 자식 댓글들
    const childComments = $('.comment[data-parent-co-no="' + coNo + '"]')

    // 부모 댓글 댓글수 엘레멘트에 자식 댓글 갯수 넣기
    $('.comment[data-co-no="' + coNo + '"] .comment-count').html(childComments.length)

    // 자식 댓글 0개면 함수 종료
    if (childComments.length <= 0) return

    // 자식 댓글 있다면 순회하며 함수 재귀 실행(자식 댓글이 0개일 때까지)
    $.each(childComments, function () {
        countChildComments($(this).data('co-no'))
    })
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

    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')

    commentSection.toggle()

    if (commentSection.css('display') === 'block') {

        const data = {ansNo: ansNo}

        commentSection.find('.comment-list').html(getComments(data))

        commentSection.find('.comment[data-co-level="1"]').last().removeClass('border-bottom-gray')

        // 마지막 댓글일 경우 댓글 더보기 버튼 숨기기
        if (commentSection.find('.last-checker').last().data('is-last') === "Y") {

            $(this).find('.view-more-comments').addClass('hidden')
        }

        $.each(commentSection.find('.comment'), function (index, item) {
            recursiveCountChildComments($(item).data('co-no'))
        })

        // popover 초기화
        initPopovers()

    }
})

// 댓글 popover 옵션
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

                // 삭제 댓글이 마지막 댓글일 수 있으므로 댓글이 포함되어 있었던 comment-section의 마지막 댓글 bottom border 지우기
                commentSection.find('.comment[data-co-level="1"]').last().removeClass('border-bottom-gray')
                
                // popover 숨기기
                $(this).parents('.popover').popover('hide')

                // 해당 댓글의 부모 댓글의 자식 댓글 카운트 업데이트
                countChildComments($(this).data('parent-co-no'))

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

// 댓글 더보기
$('.view-more-comments').click(function () {

    const ansNo = $(this).data('ans-no')

    const commentSection = $('.comment-section[data-ans-no="' + ansNo + '"]')

    const data = {ansNo: ansNo,
                  lastCoNo: commentSection.find('.comment[data-co-level="1"]').last().data('co-no')}

    commentSection.find('.comment-list').append(getComments(data))

    // popover 초기화
    initPopovers()

    // 마지막 댓글 border-bottom 지우기
    commentSection.find('.comment[data-co-level="1"]').last().removeClass('border-bottom-gray')

    // 마지막 댓글일 경우 댓글 더보기 버튼 숨기기
    if (commentSection.find('.last-checker').last().data('is-last') === "Y") {

        $(this).addClass('hidden')
    }


})

// 댓글 등록
$('.comment-section').on('click', '.add-comment-button', function () {

    $.ajax({
        url: '/comment/create',
        type: 'POST',
        data : $(this).parent().find('.comment-form').serialize(),
        context: this,
        success: function (comment) {

            const parentCoNo = $(this).data('co-no')

            if (parentCoNo === 0) { // 부모 댓글 없을 때 댓글리스트 맨 위에 추가

                $(this).parents('.comment-section').find('.comment-list').prepend(comment)

            } else { // 부모 댓글 있을 때 부모 댓글의 바로 아래에 추가

                // 댓글 추가
                $('.comment[data-co-no="' + parentCoNo + '"]').after(comment)

                // 추가한 댓글의 level이 1보다 커서 template에서 숨겨지므로 수동으로 보여주기
                $('.comment[data-parent-co-no="' + parentCoNo + '"]').show()

            }


            // popover 초기화
            initPopovers()

            // 해당 댓글의 부모 댓글의 자식 댓글 카운트 업데이트
            countChildComments($(this).data('co-no'))

        },
        error: function () {
            console.log('댓글 등록에 실패했습니다.')
        }
    })
})

