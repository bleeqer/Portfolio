//
// $(window).scroll(function() {
//
//     if ($(window).scrollTop() + 0.4 >= $(document).height() - $(window).height()) {
//
//         const lastQuesNo = $('.pair').last().data('ques-no')
//         const categoryCode = $('.category-item.selected').data('category-code')
//
//         $.ajax({
//             url: '/more',
//             type: 'GET',
//             contentType: 'application/json',
//             data : {'quesNo': lastQuesNo, categoryCode: categoryCode},
//             success: function (pairs) {
//
//                 $('#pair-list').append(pairs)
//
//             },
//             error: function () {
//                 console.log('error occurred')
//             }
//         })
//     }
// })
//

$(window).scroll(function() {

    if ($(window).scrollTop() + 1 >= $(document).height() - $(window).height()) {

        const lastQuesNo = $('#pair-list .pair').last().data('ques-no')
        const categoryCode = $('.category-item.selected').data('category-code')


        $.ajax({
            url: '/more',
            type: 'GET',
            contentType: 'application/json',
            data : {'quesNo': lastQuesNo, categoryCode: categoryCode},
            success: function (pairs) {
                pairs.forEach(function (pair) {
                    $('#pair-list').append(addPair($(pair)[0]))
                })

                // if (isExist('.question')) $('#no-content').hide()

                initAnswerPopover()

            },
            error: function () {
                console.log('error occurred')
            }
        })
    }
})