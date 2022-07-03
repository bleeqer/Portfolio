
$(window).scroll(function() {

    if ($(window).scrollTop() + 0.1 >= $(document).height() - $(window).height()) {

        // 숨겨진 질문 3개 선택
        const answers = $('.answer:hidden').slice(0, 3)

        // 선택된 3개 질문 보여주기
        answers.show()

    }
})