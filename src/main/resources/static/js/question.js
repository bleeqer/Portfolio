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

    const categoryCode = $('#category-select option:selected').attr('value')

    $('#category-code').val(categoryCode)
    })

// 질문하기 버튼 클릭 시 카테고리 조회하여 모달창에 셋팅
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

// 질문 등록 버튼 클릭 시
$('#add-question-button').click(function () {

    $('#question-form').submit()

    // $.ajax({
    //     url: '/question/create',
    //     type: 'POST',
    //     data: $('#question-form').serialize(),
    //     beforeSend: function(xhr){
    //         xhr.setRequestHeader(header, token)
    //     },
    //     success: function (question) {
    //         window.location.href = '/questions'
    //         alert(question)
    //         // 답변 대기 질문 화면으로 리디렉트하고 첫번째 리스트에 질문 추가
    //         // 등록된 답변을 답변 리스트에 추가
    //         // $('#question-list').prepend(question)
    //         // initAnswerPopover()
    //
    //         // 모달창 종료
    //         // $('#question-list')
    //     }
    // })
})
