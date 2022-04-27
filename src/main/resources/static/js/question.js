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