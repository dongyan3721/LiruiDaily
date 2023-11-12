<%--
  Created by IntelliJ IDEA.
  User: 29145
  Date: 2023/10/21
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据统计</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/download3">
    <input type="hidden" name="op1" value="1">
    <button onclick="onsubmit">下载第一个文件</button>
</form>
<form action="${pageContext.request.contextPath}/download3">
    <input type="hidden" name="op2" value="2">
    <button onclick="onsubmit">下载第二个文件</button>
</form>
<form action="${pageContext.request.contextPath}/download3">
    <input type="hidden" name="op3" value="3">
    <button onclick="onsubmit">下载第三个文件</button>
</form>
</body>
</html>
