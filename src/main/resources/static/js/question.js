//
//
// // 답댓글 보기 버튼 클릭
// $('.view-reply-button').click(function () {
//
//     // 댓글 입력 input 보이기
//     $(this).parents('.comment').find('.reply-input-container').removeClass('hidden')
//
//     // 답댓글의 부모 번호(현재 댓글 번호)
//     const parentComment = $(this).parents('.comment').data('comment')
//
//     // 답댓글 계층
//     const level = $(this).parents('.comment').data('comment-level') + 1
//
//     // 숨겨져있던 답댓글 보이기
//     $('.comment[data-parent-comment="' + parentComment + '"][data-comment-level="' + level + '"]').removeClass('hidden')
//
// })
