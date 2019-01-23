<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API 测试</title>
</head>
<body>
    <p id="test-data"></p>
</body>
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script>
    $.get("/spittlesx", function (data) {
        console.log(JSON.stringify(data));
        $('#test-data').text(JSON.stringify(data));
    });
</script>
</html>
