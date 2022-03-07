<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 5:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="modal-user" class="modal">

    <form id="userLogin-form" action="" method="POST">

        <input type="text" id="userId" name="userId" placeholder="Enter ID" autocomplete="off">

        <input type="password" id="userPW" name="userPW" placeholder="Enter password" autocomplete="off">

        <sec:csrfInput/>

        <div id="form-footer">
            <input type="submit" id="userLogin-submit" class="btn">Login</input>
        </div>
    </form>
</div>
