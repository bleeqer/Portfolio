
$(window).scroll(function() {

    if ($(window).scrollTop() + 0.1 >= $(document).height() - $(window).height()) {

        const lastQuesNo = $('#pair-list .pair').last().data('ques-no')
        const categoryCode = $('.category-item.selected').data('category-code')
        const keyword = $('#search-data #search-keyword').val()

        $.ajax({
            url: '/more',
            type: 'GET',
            contentType: 'application/json',
            data : {quesNo: lastQuesNo, categoryCode: categoryCode, searchKeyword: keyword},
            success: function (pairs) {

                pairs.forEach(function (pair) {
                    $('#pair-list').append(addPair($(pair)[0]))
                })

                initAnswerPopover()

            },
            error: function () {
                console.log('error occurred')
            }
        })
    }
})