
<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 6:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<form id="userLogin-form" action="/user/loginAJAX" method="POST">
    <label for="id">ID</label>
    <input type="text" name="username" id="id">
    <br>
    <label for="userPW">PASSWORD</label>
    <input type="password" name="password" id="userPW">
    <button id="userLogin-submit" type="submit">login</button>
    <span id="register-button">Register</span>
    <sec:csrfInput/>
</form>

<%@ include file="/WEB-INF/views/modals/userRegisterForm.jsp" %>

<script>
    $(document).ready(function() {


        $("#register-button").click(async function (e) {

            e.preventDefault()

            $("#userRegister-form").attr("action", "/user/register/")

            await $("#modal-userRegister").modal("show")

            await initEditor()

        })

        $("#userRegister-submit").on('click', function(e) {

            e.preventDefault()

            const form = $("#userRegister-form")

            // if (check_pw(form.find("#pw").val()) !== check_pw(form.find("#pw2").val())) {
            //
            //     alert("비밀번호가 일치하지 않습니다")
            //     return
            //
            // }

            //ajax form submit
            $.ajax({
                type: "POST",
                url: "/user/register/",
                data: form.serialize(),
                success: function () {
                    alert("회원 가입 성공")
                }
            })

        })
        const csrfHeaderName = "${_csrf.headerName}"
        const csrfTokenValue = "${_csrf.token}"

        $("#userLogin-submit").on('click', function(e) {

            e.preventDefault()
            const form = $("#userLogin-form")

            // if (check_pw(form.find("#pw").val()) !== check_pw(form.find("#pw2").val())) {
            //
            //     alert("비밀번호가 일치하지 않습니다")
            //     return
            //
            // }



            //ajax form submit
            $.ajax({
                type: "POST",
                url: "/user/login.ajax",
                data: form.serialize(),

                beforeSend: function(xhr){
                    xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
                },

                dataType: "json",

                success: function (res) {
                    if (res.result === "fail") {
                        alert(res.msg)
                    } else if (res.result === "success") {
                        window.location.href = "/"
                    }
                },
                error: function(error) {
                    alert("error:" + error);
                }
            })
        })
    })

</script>
</body>
<head>
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- jQuery Modal -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

</head>
</html>