

$(window).scroll(function() {

    if ($(window).scrollTop() + 0.5 >= $(document).height() - $(window).height()) {

        const url = $(location).attr('pathname') + ($(location).attr('pathname') === '/' ? 'more' : '/more')

        const lastQuesNo = $('.question-container').last().data('ques-no')

        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            data : {'quesNo': lastQuesNo},
            success: function (result) {
                $('.question-container').parent().append(result)

            },
            error: function () {
                console.log('error occurred')
            }
        })
    }
})
