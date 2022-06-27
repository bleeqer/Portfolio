// 댓글 popover option 초기화
function initCommentPopover() {
    $('.comment-option-button').each(function (idx, element) {

        $(function() {
            $(element).popover({
                trigger: 'focus',
                html: true,
                sanitize: false,
                content: $(element).find($('.comment-option-popover-content')).html()
            })
        })
    })
}



// 댓글 가져오기
function getComments(data) {
// 댓글 더 불러오기에서 사용 시 more에 true 전달할 것(더 불러오기는 댓글 리스트 맨 아래에 추가)
    let comments

    console.log(data)

    $.ajax({
        url: '/comment/',
        type: 'GET',
        dataType: 'json',
        async: false,
        data : data,
        // data : data,
        success: function (comments) {
            comments.forEach(function (comment) {
                addComment(comment)
            })

            resetBorderTop()

            // option popover 초기화
            initCommentPopover()

            // 자식 댓글 카운트 및 셋팅
            countChildComments()


        },
        error: function () {
            console.log('오류가 발생했습니다.')
        }
    })

    return comments
}

function isCommentValid(formEl) {
    return typeof (formEl.find('input[name="answerComment"]').val()) !== 'undefined';
}

// 자식 댓글 갯수 업데이트
function countChildComments() {

    const comments = $('.comment')

    comments.each(function (idx, comment) {

        const coNo = $(comment).data('co-no')

        const childComments = $('.comment[data-parent-co-no="' + coNo + '"]')

        if (childComments.length > 0) {

            $(comment).find('.comment-count').html(childComments.length)

        }

    })

}

// root 댓글 및 하위댓글 모두 지우기
function deleteCommentTree (rootCoNo) {

    if (typeof rootCoNo === 'undefined') {
        return
    }

    // 해당 댓글 지우기
    $('.comment-section .comment[data-co-no="' + rootCoNo + '"]').remove()

    deleteCommentTree($('.comment-section .comment[data-parent-co-no="' + rootCoNo + '"]').data('co-no'))
}

// 마지막 댓글 번호 반환
function getLastCoNo (ansNo) {
    let coNoArr = []

    $('.comment[data-ans-no="' + ansNo + '"]').each(function (idx, el) {
        coNoArr.push($(el).data('co-no'))
    })

    return Math.max.apply(Math, coNoArr)
}

function isLastComment (coNo, ansNo) {

    let isLast = false
    $.ajax({
        url: '/comment/checkLast',
        type: 'GET',
        async: false,
        data: {coNo: coNo, ansNo: ansNo},
        success: function (result) {
            isLast = result
        }
    })

    return isLast
}

// 댓글 보기, 추가 시 border-top-gray 리셋
function resetBorderTop () {

    const rootComments = $('.comment[data-co-level="1"]')

    rootComments.addClass('border-top-gray')
    rootComments.first().removeClass('border-top-gray')
}

// 템플릿에 전달받은 댓글 데이터 세팅 후 화면에 삽입
function addComment (comment) {

    let template = $('#comment-template')
    
    // comment data 속성 셋팅
    template.find('.comment').attr('data-co-level', comment.level)
    template.find('.comment').attr('data-ans-no', comment.ansNo)
    template.find('.comment').attr('data-co-no', comment.coNo)
    template.find('.comment').attr('data-parent-co-no', comment.parentCoNo)
    template.find('.comment-form').attr('data-co-no', comment.coNo)
    template.find('.comment-form').attr('data-ans-no', comment.ansNo)
    
    // comment 셋팅
    template.find('.comment-user-photo').attr('src', '/uploadedImages' + comment.userPhoto)
    template.find('.comment-text p').html(comment.answerComment)
    template.find('.comment-user-name').html(comment.userName)
    template.find('.comment-reg-date').html(comment.regDate)

    // comment option popover 셋팅
    template.find('.comment-popover-item').attr('data-co-no', comment.coNo)
    template.find('.comment-popover-item').attr('data-ans-no', comment.ansNo)

    // 로그인 유저와 댓글 작성자가 같을 때 옵션 버튼 활성화
    if ($('#logged-in-user').val() !== comment.userEmail) {
        template.find('.comment-option-button').hide()
    } else {
        template.find('.comment-option-button').show()
    }

    // 계층에 따른 설정
    if (comment.level === 1) {

        // 사진 크기 조절
        template.find('.comment-user-photo').css('width', 36)
        template.find('.comment-user-photo').css('height', 36)

        // 왼쪽 패딩 조절
        template.find('.comment').show()
        template.find('.comment').css('padding-left', 0)

        // 댓글의 답변글
        const answer = $('.answer[data-ans-no="' + comment.ansNo + '"]')

        template.find('.comment .reply-input-container').hide()

        // 더보기일 경우 코멘트 리스트 맨 마지막에 추가
        // if (more) {
        answer.find('.comment-list').append(template.html())
        //
        // // 더보기 아닐 경우 코멘트 리스트 맨 위에 추가
        // } else {
        //     answer.find('.comment-list').prepend(template.html())
        // }

    } else {
        
        // 사진 크기 조절
        template.find('.comment-user-photo').css('width', 24)
        template.find('.comment-user-photo').css('height', 24)
        
        // 왼쪽 패딩 조절
        const padding = (comment.level - 2) * 36 + 42
        template.find('.comment').css('padding-left', padding + 'px')
        
        // 하위 댓글이므로 숨김 후 부모댓글 바로 아래에 추가
        template.find('.comment').hide()
        template.find('.comment .reply-input-container').hide()
        $('.comment[data-co-no="' + comment.parentCoNo + '"]').after(template.html())
    }

}

// 답변글의 댓글 보기
$('body').on('click', '.comment-button', function () {

    const answer = $(this).closest('.answer')

    const ansNo = answer.data('ans-no')

    const commentSection = answer.find('.comment-section')

    commentSection.toggle()

    if (commentSection.css('display') === 'block') {
        
        answer.find('.comment-list').html(getComments({ansNo: ansNo}))

    } else {

        // 댓글창 닫을 때 모든 댓글 삭제(새로고침 목적)
        $('.comment-section .comment').remove()

    }
})

// 댓글 popover 옵션
$(document).on('click', '.comment-popover-item', function () {

    const coNo = $(this).data('co-no')
    const ansNo = $(this).data('ans-no')
    const optionType = $(this).data('option-type')

    if (optionType === 'Delete') {
        
        $.ajax({
            type: 'POST',
            url: '/comment/delete',
            data: JSON.stringify({coNo: coNo, ansNo: ansNo}),
            contentType: 'application/json',
            dataType: 'json',
            context: this,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token)
            },
            success: function (deletedCoNo) {

                // 해당 댓글 포함 하위 댓글 모두 화면에서 지우기
                deleteCommentTree(deletedCoNo)

                // 댓글 border-top-gray 리셋
                resetBorderTop()

                // 자식 댓글 카운트 및 셋팅
                countChildComments()

            },
            error: function () {
                alert('오류가 발생했습니다.')
            }
        })
    }

    else if (optionType === 'Edit') {

        const comment = $('.comment[data-co-no="' + coNo + '"]')

        comment.find('.comment-text').hide()
        comment.find('.comment-footer').hide()
        comment.find('.comment-edit-container').show()

        $.ajax({
            type: 'GET',
            url: '/comment/select',
            data: {coNo: coNo},
            contentType: 'application/json',
            success: function (originalComment) {

                comment.find('.edit-comment-text').val(originalComment.answerComment)
                comment.find('.co-no').val(originalComment.coNo)
                comment.find('.user-email').val(originalComment.userEmail)

            },
            error: function () {
                alert('오류가 발생했습니다.')
            }
        })


    }
})


$('.comment-section').on('click', '.comment-edit-submit-button', function () {

    const comment = $(this).closest('.comment')
    const commentForm = comment.find('.comment-edit-form')


    if (!isCommentValid(commentForm)) {
        alert('내용을 입력해주세요.')
        return false
    }

    $.ajax({
        url: '/comment/update',
        type: 'POST',
        data: commentForm.serialize(),
        success: function (updatedComment) {
            comment.find('.comment-text').show()
            comment.find('.comment-footer').show()
            comment.find('.comment-edit-container').hide()

            // 수정된 댓글 내용으로 바꾸기
            comment.find('.comment-text').html(updatedComment.answerComment)

        }
    })
})

// 대댓글 보기
$('.comment-section').on('click', '.reply-button', function () {

    const parentNo = $(this).parents('.comment').data('co-no')
    
    // 해당 댓글의 자식 댓글
    const childComments = $('.comment[data-parent-co-no="' + parentNo + '"]')

    // 보여지고 있는 자식 댓글 선택
    let shownChildComments = childComments.filter(function() {
        return $(this).is(':visible')
    })

    // reply button 클릭 시 이미 보여지고 있는 자식 댓글이 있다면 전부 숨기기
    if (shownChildComments.length > 0) {

        shownChildComments.hide()

    // 보여지고 있는 자식 댓글이 없다면 특정 갯수 보여주기
    } else {
        childComments.slice(0, 3).show()

        // 보여지고 있는 자식 댓글 다시 선택
        shownChildComments = childComments.filter(function() {
            return $(this).is(':visible')
        })

        // 보여지고 있는 자식 댓글의 마지막 엘레멘트가 전체 자식댓글의 마지막 엘레멘트가 아닐 경우 view more replies 버튼 보여주기
        if (shownChildComments.last().data('co-no') < childComments.last().data('co-no')) {
            shownChildComments.last().find('.view-more-reply-container').show()
        }
    }

    // reply 입력 input 토글
    $('.comment[data-co-no="' + parentNo + '"] .reply-input-container').toggle()
})

$('.comment-section').on('click', '.view-more-reply-container', function () {

    const parentCoNo = $(this).data('parent-co-no')

    const childComments = $('.comment[data-parent-co-no="' + parentCoNo + '"]')


    // 해당 댓글의 숨겨져있는 자식 댓글
    const hiddenChildComments = childComments.filter(function () {
        return $(this).is(':hidden')
    })

    // 특정 갯수만 보여주기
    hiddenChildComments.slice(0, 3).toggle()

    // 현재 view more replies 버튼 숨기기
    $(this).hide()

    // 보여지고 있는 자식 댓글 선택
    let shownChildComments = childComments.filter(function() {
        return $(this).is(':visible')
    })

    // 보여지고 있는 자식 댓글의 마지막 엘레멘트가 전체 자식댓글의 마지막 엘레멘트일 경우 view more replies 버튼 숨기기
    if (shownChildComments.last().data('co-no') >= childComments.last().data('co-no')) {

        shownChildComments.last().find('.view-more-reply-container').hide()

    // 마지막 엘레멘트가 아닐 경우 view more replies 버튼 보여주기
    } else {
        shownChildComments.last().find('.view-more-reply-container').show()
    }

})

// 댓글 더보기
$('body').on('click', '.view-more-comments', function () {

    const ansNo = $(this).closest('.answer').data('ans-no')

    const lastCoNo = getLastCoNo(ansNo)

    // 마지막 댓글 여부 체크 후 맞다면 더보기 버튼 숨김
    if (isLastComment(lastCoNo, ansNo) === true) {
        $(this).addClass('hidden')

        return false
    }

    getComments({ansNo: ansNo,
                 coNo: lastCoNo},
                true)


})

// 댓글 등록
$('body').on('click', '.add-comment-button', function () {


    const ansNo = $(this).parents('.answer').data('ans-no')

    let parentCoNo = $(this).closest('.comment').data('co-no')

    if (typeof (parentCoNo) === 'undefined') {
        parentCoNo = 0
    }

    const commentForm = $('.comment-form[data-co-no="' + parentCoNo + '"][data-ans-no="' + ansNo + '"]')

    commentForm.find('input[name="parentCoNo"]').val(parentCoNo)
    commentForm.find('input[name="ansNo"]').val(ansNo)

    if (!isCommentValid(commentForm)) {
        alert('내용을 입력해주세요.')
        return false
    }

    $.ajax({
        url: '/comment/create',
        type: 'POST',
        data : commentForm.serialize(),
        context: this,
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        success: function (comment) {
            addComment(comment)

            // 댓글 border-top-gray 리셋
            resetBorderTop()

            // option popover 초기화
            initCommentPopover()

            // 자식 댓글 카운트 및 셋팅
            countChildComments()

            // 댓글마다 like/dislike 여부 조회해서 highlight 하기
            commentLikeHighlight()

            // 새로 등록된 댓글은 level에 관계없이 보이기
            $('.comment[data-co-no="' + comment.coNo + '"]').show()

        },
        error: function () {
            console.log('댓글 등록에 실패했습니다.')
        }
    })
})

