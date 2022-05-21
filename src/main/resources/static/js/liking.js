$('.like-button').click(function () {

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        data: {
            ansNo: $(this).data('ans-no')
        },
        context: this,
        success: function (likeCnt) {

            if (likeCnt > 0) {

                $(this).find('.like-cnt').html(likeCnt)
            } else {

                $(this).find('.like-cnt').html('')
            }

        },
        error: function () {
            alert('오류가 발생했습니다.')
        }

    })
})

// $(function() {
//     // 추천버튼 클릭시(추천 추가 또는 추천 제거)
//     $("#rec_update").click(function () {
//         $.ajax({
//             url: "/expro/RecUpdate.do",
//             type: "GET",
//             data: {
//                 no: '${content.board_no}',
//                 id: '${id}'
//             },
//             success: function () {
//                 alert('gg')
//             }
//         })
//     })
// })

