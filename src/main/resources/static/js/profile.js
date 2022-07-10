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

        },
        error: function (error) {
            alert(error.responseText)
        }
    })

})

$('#profile-edit-submit-button').click(function () {

    if (isUserValid()) {
        $.ajax({
            url: '/user/update',
            type: 'POST',
            data: new FormData($('#signUp-form')[0]),
            dataType: 'text',
            contentType: false,
            processData: false,
            context: this,
            success: function (success) {

                alert(success)

                location.reload()

                // $('#signUp-modal').modal('hide')
                //
                // const user = getUser()

                // $('#navbar-profile').attr('src', '/uploadedImages' + user.photo)
                // $('#profile-photo').attr('src', '/uploadedImages' + user.photo)
                // $('#profile-username').text(user.name)
                // $('#profile-occupation').text(user.occupation)

            },
            error: function (error) {

                alert(error)

            }
        })
    }
})

