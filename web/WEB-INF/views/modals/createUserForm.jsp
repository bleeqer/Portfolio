<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 5:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="modal-user" class="modal">

    <form id="user-form" action="/user/register/" method="POST" enctype="multipart/form-data">

        <input type="text" id="userId" name="userId" placeholder="Enter ID">

        <input type="password" id="password" name="password" placeholder="Enter password">

        <div id="form-footer">
            <button type="submit" id="submit-button" class="btn">Next</button>
        </div>
    </form>
</div>
