<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/15/22
  Time: 6:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VIEW POST</title>
    TITLE : ${post.title}
    CONTENT : ${post.content}
    WRITER : ${post.writer}
    <a href="/board/edit/${post.bno}">EDIT</a>
    <a href="/board/delete/${post.bno}">DELETE</a>
</head>
<body>

</body>
</html>
