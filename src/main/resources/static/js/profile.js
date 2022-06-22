// navbar 버튼 클릭 시 버튼 활성화
$('a').each(function () {
    let splitCurrentURL = $(location).attr('pathname').split('/')

    currentTab = splitCurrentURL[splitCurrentURL.length - 1]

    $('.profile-tab').removeClass('color-red')
    $('.profile-tab .under-red').addClass('hidden')

    if ('questions' === currentTab) {

        $('#question-tab').addClass('color-red')
        $('#question-tab .under-red').removeClass('hidden')

    } else if ('answers' === currentTab) {

        $('#answer-tab').addClass('color-red')
        $('#answer-tab .under-red').removeClass('hidden')

    }

})