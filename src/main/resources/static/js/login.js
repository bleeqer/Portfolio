// <script>
//     $(document).ready(function() {
//
//
//     $("#register-button").click(async function (e) {
//
//         e.preventDefault()
//
//         $("#userRegister-form").attr("action", "/user/register/")
//
//         await $("#modal-userRegister").modal("show")
//
//         await initEditor()
//
//     })
//
//     $("#userRegister-submit").on('click', function(e) {
//
//     e.preventDefault()
//
//     const form = $("#userRegister-form")
//
//     // if (check_pw(form.find("#pw").val()) !== check_pw(form.find("#pw2").val())) {
//     //
//     //     alert("비밀번호가 일치하지 않습니다")
//     //     return
//     //
//     // }
//
//     //ajax form submit
//     $.ajax({
//     type: "POST",
//     url: "/user/register/",
//     data: form.serialize(),
//     success: function () {
//     alert("회원 가입 성공")
// }
// })
//
// })
//     const csrfHeaderName = "${_csrf.headerName}"
//     const csrfTokenValue = "${_csrf.token}"
//
//     $("#userLogin-submit").on('click', function(e) {
//
//     e.preventDefault()
//     const form = $("#userLogin-form")
//
//     // if (check_pw(form.find("#pw").val()) !== check_pw(form.find("#pw2").val())) {
//     //
//     //     alert("비밀번호가 일치하지 않습니다")
//     //     return
//     //
//     // }
//
//
//
//     //ajax form submit
//     $.ajax({
//     type: "POST",
//     url: "/user/login.ajax",
//     data: form.serialize(),
//
//     beforeSend: function(xhr){
//     xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
// },
//
//     dataType: "json",
//
//     success: function (res) {
//     if (res.result === "fail") {
//     alert(res.msg)
// } else if (res.result === "success") {
//     window.location.href = "/"
// }
// },
//     error: function(error) {
//     alert("error:" + error);
// }
// })
// })
// })
//
// </script>
