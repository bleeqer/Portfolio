$('.search-input').on('keyup', (function () {
    const keyword = $(this).val()
    const keywordDisplay = $('.display-keyword')

    keywordDisplay.html(keyword)

    if (keyword.length > 0) {
        $('.search-display').removeClass('hidden')

    } else {
        $('.search-display').addClass('hidden')
    }
}))

$('.search-input').focus(function () {
    $('.search-overlay').addClass('show-search-overlay')

})

$('.search-input').focusout(function () {
    $('.search-overlay').removeClass('show-search-overlay')
})

// navbar 버튼 클릭 시 버튼 활성화
$('a').each(function () {
    let currentURL = '/' + $(location).attr('pathname').split('/')[1]

    // 토픽 검색 시에도 홈메뉴 하이라이트 유지
    if (currentURL === '/topic') {
        currentURL = '/'
    }

    if ($(this).attr('href') === currentURL) {
        $(this).find('path').addClass('svg-fill')
        $(this).find('.under-red').removeClass('hidden')
    }

})

$('#search-button').click(function () {

    $('#search-form').attr('action', '/search/questions')
    $('#search-form').submit()
    //
    // $.ajax({
    //     type: 'GET',
    //     url: 'search/questions',
    //     data: $('#search-form').serialize(),
    //     success: function (questions) {
    //
    //     }
    // })

})

// $('#ask-question-button').click(function () {
//     $('#question-modal').modal('show')
// })

let user = getUser()

$('#navbar-profile').attr('src', '/uploadedImages' + user.photo)

function getUser() {

    let userInfo = ''
    $.ajax({

        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        success: function (user) {
            userInfo = user

        },
        error: function () {
        }
    })

    return userInfo
}