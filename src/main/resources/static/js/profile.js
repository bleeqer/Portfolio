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

    } else {

        $('#answer-tab').addClass('color-red')
        $('#answer-tab .under-red').removeClass('hidden')

    }

})

$('#profile-edit-button').click(function () {

    $.ajax({
        url: '/user/get',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        context: this,
        success: function (user) {

            $('#signUp-modal #register-button').hide()
            $('#signUp-modal #profile-edit-submit-button').show()
            $('#signUp-modal #register-email').val(user.email)
            $('#signUp-modal #profile-upload-image').attr('src', '/uploadedImages' + user.photo)
            $('#register-email').prop('readonly', true)
            // $('#signUp-modal #register-photo').filen(user.photo)
            $('#signUp-modal #register-name').val(user.name)
            $('#signUp-modal #register-occupation').val(user.occupation)

            $('#signUp-modal').modal('show')

        }
    })

})

$('#profile-edit-submit-button').click(function () {

    if (isValid()) {
        $.ajax({
            url: '/user/update',
            type: 'POST',
            data: new FormData($('#signUp-form')[0]),
            contentType: false,
            processData: false,
            context: this,
            success: function () {

                alert("업데이트를 완료 했습니다.")
                $('#signUp-modal').modal('hide')

            },
            error: function () {

                alert("업데이트를 실패 했습니다.")

            }
        })
    }
})

