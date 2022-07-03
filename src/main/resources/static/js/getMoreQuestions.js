
$(window).scroll(function() {

    if ($(window).scrollTop() + 0.1 >= $(document).height() - $(window).height()) {

        const lastQuesNo = $('#question-list .question').last().data('ques-no')
        const categoryCode = $('.category-item.selected').data('category-code')
        const keyword = $('#search-data #search-keyword').val()

        $.ajax(
            {
            url: '/questions/more',
            type: 'GET',
            contentType: 'application/json',
            data : {quesNo: lastQuesNo, categoryCode: categoryCode, searchKeyword: keyword},
            success: function (questions) {
                questions.forEach(function (question) {
                    $('#question-list').append(addQuestion($(question)[0]))
                })

                // if (isExist('.question')) $('#no-content').hide()

                initAnswerPopover()

            },
                error: function (error) {
                    alert(error.responseText)
                }
        })
    }
})