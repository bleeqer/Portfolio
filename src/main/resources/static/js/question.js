$(function () {
    $('[data-bs-toggle="popover"]').popover({
        html: true,
        content: function() {
            return $('#sort-popover-content').html();
        }
    })
})


$('html').click(function (e) {

    // popover 아이템 클릭 시
    $('.sort-popover-item').click(function () {

        // mini-checker, highlighting 초기화
        $('.sort-popover-item .sort-mini-checker').addClass('hidden')
        $('.sort-popover-item').removeClass('bg-whiten-light')

        // 클릭된 popover item만 mini-checker, highlighting 표시
        $(this).find('.sort-mini-checker').removeClass('hidden')
        $(this).addClass('bg-whiten-light')

    })

    if (e.target.id !== 'sort-button') {
        $('[data-bs-toggle="popover"]').popover('hide')
    }
})


// 답댓글 보기 버튼 클릭
$('.view-reply-button').click(function () {

    // 댓글 입력 input 보이기
    $(this).parents('.comment').find('.reply-input-container').removeClass('hidden')

    // 답댓글의 부모 번호(현재 댓글 번호)
    const parentComment = $(this).parents('.comment').data('comment')

    // 답댓글 계층
    const level = $(this).parents('.comment').data('comment-level') + 1

    // 숨겨져있던 답댓글 보이기
    $('.comment[data-parent-comment="' + parentComment + '"][data-comment-level="' + level + '"]').removeClass('hidden')

})
