$('.like-button').click(function () {

    $.ajax({
        url: '/answer/like',
        type: 'GET',
        data: {
            ansNo: $(this).data('ans-no')
        },
        success: function () {
            alert('gg')
        },
        error: function () {
            alert('ㅡㅡ')
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

