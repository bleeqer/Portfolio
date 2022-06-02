$('#ask-question-button').click(function () {

    $.ajax({
        url: '/question/categories',
        type: 'GET',
        success: function (categories) {
            let cats = '';
            categories.forEach(function (cat) {

                cats += ('<option value="' + cat.categoryCode + '">' + cat.categoryName + '</option>')
            })

            // 카테고리 리스트에 카테고리 목록 추가
            $('#category-select').append(cats)
        }
    })
})

$('#category-select').change(function () {

    const category = $('#category-select option:selected').text()
    const categoryCode = $('#category-select option:selected').attr('value')

    $('#category-name').val(category)
    })

$('#ask-question-button').click(function () {

    $.ajax({
        url: '/question/categories',
        type: 'GET',
        success: function (categories) {
            let cats = '';
            categories.forEach(function (cat) {

                cats += ('<option value="' + cat.categoryCode + '">' + cat.categoryName + '</option>')
            })

            // 카테고리 리스트에 카테고리 목록 추가
            $('#category-select').append(cats)
        }
    })
})

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
