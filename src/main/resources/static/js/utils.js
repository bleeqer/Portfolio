// element height 초과 체크 함수
function isOverflown(element) {
    return element.prop('scrollHeight') > element.height()
}

function isExist(selector) {

    return $(selector).length-1 > 0
}