$('.readMore-button').on('click', function() {
    $(this).parent().find('.fade-post').css('max-height', $(this).parent().find('.fade-post').prop('scrollHeight') + 'px')
    $(this).hide()
    $(this).parent().find('.overflow-fade').css('display', 'none')
})

$('.fade-post').each(function() {

    if (isOverflown($(this))) {
        $(this).find('.overflow-fade').removeClass('hidden')
        $(this).parent().find('.readMore-button').removeClass('hidden')
    }
})

// 답변 버튼 클릭 시 답변 modal에 유저정보, 질문글 띄우기
$('.answer-button').click(function () {

    $.ajax({
        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        context: this,
        success: function (user) {
            $('#answer-modal').find('#user-img').attr('src', user.photo)
            $('#answer-modal').find('#user-name').html(user.name)
            $('#answer-modal').find('#user-occupation').html(user.occupation)
            $('#answer-modal').find('#asked-question').html($('.question[data-ques-no="' + $(this).data('ques-no') + '"]').find('.question-text').text())

        }
    })
})