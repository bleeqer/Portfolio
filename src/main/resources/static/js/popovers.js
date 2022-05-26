// 질문, 답변 정렬 popover
// $(function () {
//     $('.popover-test[data-bs-toggle="popover"]').popover({
//         html: true,
//         content: function() {
//             return $('#option-popover-content').html()
//         }
//     })
// })

// 댓글 옵션 popover
$(".question-container").popover({
    trigger: "click"
});

// $(document).on("click", ".popover-test", function(e) {
//     $(e.target).popover({
//         html: true,
//         content: function() {
//             return $('#option-popover-content').html()
//         }
//     })
// });


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
