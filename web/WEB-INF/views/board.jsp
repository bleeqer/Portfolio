<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/13/22
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>게시판</h2>
    <table>
        <tr>
            <th>BNO</th>
            <th>TITLE</th>
            <th>WRITER</th>
            <th>DATE</th>
            <th>VIEWS</th>
        </tr>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.bno}</td>
                <td><a href="/board/${post.bno}">${post.title}</a></td>
                <td>${post.writer}</td>
                <td>${post.regDate}</td>
                <td>${post.viewCnt}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
