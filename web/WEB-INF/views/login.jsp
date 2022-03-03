<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 3/3/22
  Time: 6:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/user/register/" method="POST">
        <label for="id">ID</label>
        <input type="text" name="userId" id="id">
        <br>
        <label for="userPW">PASSWORD</label>
        <input type="password" name="userPW" id="userPW">
        <button type="submit">register</button>
    </form>
</body>
</html>
