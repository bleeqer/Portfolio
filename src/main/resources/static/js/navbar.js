$('body').on('click', '.log-out', function (e) {
    console.log("?")
    e.preventDefault()

    $('#logout-form').submit()
})

// enter키로 검색 submit 방지
$('.search-input').on('keydown', function(e) {
    if (e.keyCode === 13) {

        e.preventDefault()
    }
})

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

// 현재 url에 따라 navbar button 하이라이트
var checkPage = $(location).attr('pathname').indexOf('questions')
if (checkPage > 0) {
    $('.nav-question').find('path').addClass('svg-fill')
    $('.nav-question').find('.under-red').removeClass('hidden')
} else {
    $('.nav-pair').find('path').addClass('svg-fill')
    $('.nav-pair').find('.under-red').removeClass('hidden')
}

$('#search-button').click(function () {
    currentURL = $(location).attr('pathname')

    requestURL = '/search/pairs'

    if (currentURL.indexOf('questions') !== -1) {
        requestURL = '/search/questions'
    }

    $('#search-form').attr('action', requestURL)
    $('#search-form').submit()

    // $.ajax({
    //     type: 'GET',
    //     url: requestURL,
    //     data: $('#search-form').serialize(),
    //     success: function (questions) {
    //
    //     }
    // })

})

// $('#ask-question-button').click(function () {
//     $('#question-modal').modal('show')
// })

$('#navbar-profile').attr('src', getUser().photo)


function getUser() {

    let userInfo = ''
    $.ajax({

        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        async: false,
        contentType: 'application/json',
        success: function (fetchedUser) {

            fetchedUser.photo = '/uploadedImages' + fetchedUser.photo
            userInfo = fetchedUser

        },
        error: function () {
            userInfo = {photo: '/static/img/user.png'}
        }
    })

    return userInfo
}