<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/css/style.css" />">
</head>
<body>
<h1>API 测试</h1>
<ul>
    <li>
        <a href="<c:url value="/spittles" />" target="_blank">查看最近 Spittles</a>
    </li>
    <li>
        <a href="<c:url value="/spittles/1234"/>" target="_blank">查看某条 Spittle</a>
    </li>
    <li>
        <a href="<c:url value="/test"/>" target="_blank">提交一条 Spittle</a>
    </li>
</ul>
</body>
</html>
