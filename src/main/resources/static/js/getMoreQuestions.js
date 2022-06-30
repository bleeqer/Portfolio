
$(window).scroll(function() {

    if ($(window).scrollTop() + 0.4 >= $(document).height() - $(window).height()) {

        const lastQuesNo = $('#question-list .question').last().data('ques-no')
        const categoryCode = $('.category-item.selected').data('category-code')

        $.ajax({
            url: '/questions/more',
            type: 'GET',
            contentType: 'application/json',
            data : {'quesNo': lastQuesNo, categoryCode: categoryCode},
            success: function (questions) {

                questions.forEach(function (question) {
                    $('#question-list').append(addQuestion($(question)[0]))
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