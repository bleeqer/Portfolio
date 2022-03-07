<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 5:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="modal-userRegister" class="modal">

    <form id="userRegister-form" action="" method="POST">

        <input type="text" id="userId" name="userId" placeholder="Enter ID" autofill="off">

        <input type="password" id="userPW" name="userPW" placeholder="Enter password" autocomplete="off">
        <input type="password" id="userPW2" name="userPW2" placeholder="Enter password again" autocomplete="off">

        <sec:csrfInput/>

        <div id="form-footer">
            <span id="userRegister-submit">Register</span>
<%--            <input class="btn" id="userRegister-submit" type="submit" style="display: none;"/>--%>
        </div>
    </form>
</div>

<script>
    function check_pw(pw, pw2) {

        return pw === pw2

    }
</script>
