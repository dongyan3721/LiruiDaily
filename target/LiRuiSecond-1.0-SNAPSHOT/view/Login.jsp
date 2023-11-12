<%--
  Created by IntelliJ IDEA.
  User: 29145
  Date: 2023/11/3
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<form action="${pageContext.request.contextPath}/chatroom" method="post">
    请输入用户名：<input type="text" name="user">
    <input type="hidden" name="captcha-id" id="captcha-id">
    请输入验证码：<input type="text" name="captcha"/>
    <div id="cap-container"><img id="captcha-img" alt="请求错误"></div>
    <button onclick="onsubmit">进入聊天室</button>
</form>

<script>
    function getCaptcha(){
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/api/captcha', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var servletResponse = JSON.parse(xhr.responseText);
                    if(servletResponse!=null){
                        console.log(servletResponse);
                        var href = "data:image/png;base64,"+servletResponse.href
                        document.getElementById("captcha-img").src = href
                        document.getElementById("captcha-id").value = servletResponse.uuid
                    }
                } else {
                    console.error('请求出错');
                }
            }
        };
        xhr.send();
    }

    document.addEventListener("DOMContentLoaded", getCaptcha)

    document.getElementById("cap-container").addEventListener("click", getCaptcha)
</script>

</body>
</html>
