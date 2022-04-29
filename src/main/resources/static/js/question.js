$(function () {
    $('[data-bs-toggle="popover"]').popover({
        html: true,
        content: function() {
            return $('#popover-content').html();
        }
    })
})


$('html').click(function (e) {

    if (e.target.id !== 'sort-button') {
        $('[data-bs-toggle="popover"]').popover('hide')
    }
})

$('.view-reply-button').click(function () {
    alert($(this).data('comment'))
    $('.child-comment[data-parent-comment="' + $(this).data('comment') + '"]').removeClass('hidden')
    // $('.child-comment-container').removeClass('hidden')
})