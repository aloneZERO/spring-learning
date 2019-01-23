<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>测试提交 Spittle</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/css/style.css" />">
</head>
<body>
</body>
<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
<script>
    let spittle = {};
    spittle.message = '我是一条测试信息~';
    $.ajax({
        url: '/spittles',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(spittle),
        asyns: false,
        // 注意：success 是不返回 xhr 对象的
        complete: function (xhr, message) {
            window.location.href = xhr.getResponseHeader("location");
        }
    });
</script>
</html>
