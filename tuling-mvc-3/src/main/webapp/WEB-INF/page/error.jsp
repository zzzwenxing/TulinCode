<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
默认异常 -内部错误：<h1>500</h1>
<h3 style="color: red">${exception.message}</h3>
<%--<h5> 异常类别：${errorType}</h5>
<pre><code>${stack}</code></pre>--%>
</body>
</html>