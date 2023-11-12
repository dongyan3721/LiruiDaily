<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>后台控制界面</title>
</head>
<body>
<h1>请在5173端口访问前台页面</h1>
<div class="form-area">
    <form action="${pageContext.request.contextPath}/yy-servlet" method="post">
        姓名：<input type="text" name="user"/><br/>
        性别：<input type="radio" name="gender" value="男" checked/>男&nbsp;&nbsp;<input type="radio" name=gender value="女"/>女<br/>
        年龄：<input type="text" name="age"/><br/>
        <div class="hobbies">
            <input type="checkbox" name="hobby" value="看书"/>看书&nbsp;&nbsp;
            <input type="checkbox" name="hobby" value="旅游"/>旅游&nbsp;&nbsp;
            <input type="checkbox" name="hobby" value="体育"/>体育&nbsp;&nbsp;
            <input type="checkbox" name="hobby" value="游戏"/>游戏&nbsp;&nbsp;
        </div>
        <button onclick="onsubmit">提交</button>
    </form>
</div>
<br/>
</body>
</html>