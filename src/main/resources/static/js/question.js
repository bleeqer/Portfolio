$('#add-question-button').click(function () {

    $.ajax({
        url: '/question/create',
        type: 'POST',
        data: $('#question-form').serialize(),
        beforeSend: function(xhr){
            xhr.setRequestHeader(header, token)
        },
        success: function (question) {

            // 답변 대기 질문 화면으로 리디렉트하고 첫번째 리스트에 질문 추가
            // 등록된 답변을 답변 리스트에 추가
            // $('#answer-list').prepend(answer)
            // initAnswerPopover()

            // 모달창 종료
            $('#question-modal').modal('toggle')
        }
    })
})
