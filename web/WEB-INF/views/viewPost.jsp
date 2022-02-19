<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/15/22
  Time: 6:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String realPath = request.getSession().getServletContext().getRealPath("uploadedImages"); %>

<html>
<head>
    <title>VIEW POST</title>
    TITLE : ${post.title}
    CONTENT : ${post.content}
    WRITER : ${post.writer} <br>


    <c:if test="${not empty files}">
        <c:forEach var="image" items="${files}" varStatus="status">
            <img src="/uploadedImages/<c:out value="${image.path}"/>" alt="" width="300" height="300">
        </c:forEach>
    </c:if>
    <a href="/board/edit/${post.bno}">EDIT</a>
    <a href="/board/delete/${post.bno}">DELETE</a>
</head>
<body>

</body>
</html>
