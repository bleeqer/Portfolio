<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 5:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modal-user" class="modal">

    <form id="user-form" action="/user/register/" method="POST">

        <input type="text" id="userId" name="userId" placeholder="Enter ID">

        <input type="password" id="userPW" name="userPW" placeholder="Enter password">

        <div id="form-footer">
            <input type="submit" id="submit-button" class="btn">Next</input>
        </div>
    </form>
</div>
