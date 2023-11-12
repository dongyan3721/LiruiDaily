<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 29145
  Date: 2023/11/3
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>chatroom</title>
    <script src="http://i.mooc.chaoxing.com/style/defstyle/js/jquery.min.js"></script>
</head>
<body>
<h1>聊天室</h1>
<iframe  style="display: none;" id="myiframe" name="myiframe"></iframe>

<textarea style="height: 400px; width: 600px" id="chat"></textarea>

<FORM action="${pageContext.request.contextPath}/accept" method="post" target="myiframe">
    <input type="hidden" name="origin" value=${name}>
    请选择你要发送消息的对象：<select name="userSel" id="mySelect">
    <%
        ArrayList<String> members = (ArrayList<String>)application.getAttribute("members");
        if(members!=null){
            for (String member : members) {
                if (!member.equals(session.getAttribute("name"))) {

    %>
    <option value="<%=member%>"><%=member%></option>
    <%
                }
            }
        }else {
            request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
        }
    %>
    </select>
    <input type="hidden" name="first" id="first-input">
    <input type="hidden"  name="leave" id="leave-input">
    发送的消息：<input type="text" name="msg" id="msg">
    <button onclick="onsubmit" id="submit-btn">发送</button>
</FORM>
<button id="exit" onclick="leaveMsg()">退出聊天室</button>
<script>
    function firstRequest(){
        /**var username = ""
        var params = new FormData();
        params.append("msg", "我来辣")
        params.append("origin", username);
        params.append("userSel", "All");
        params.append("first", "true");
        $.ajax({
            url: "http://localhost:8080/api/accept",
            type: "POST",
            cache: false,
            data: params,
            processData: false,
            contentType: false
        }).done(
            function (res){
                console.log(res)
            }
        ).fail(
            function (err){
                console.log(err)
            }
        )*/
        document.getElementById("first-input").value = "right";
        document.getElementById("mySelect").value = "All";
        document.getElementById("msg").value = "我来辣"
        document.getElementById("submit-btn").click();
        document.getElementById("first-input").value = null;
        document.getElementById("msg").value = null;
    }
    document.addEventListener("DOMContentLoaded", firstRequest);

    function leaveMsg(){
        /**var username = ""
        var params = new FormData();
        params.append("msg", "我走辣")
        params.append("origin", username);
        params.append("userSel", "All");
        params.append("leave", "true");
        $.ajax({
            url: "http://localhost:8080/api/accept",
            type: "POST",
            cache: false,
            data: params,
            processData: false,
            contentType: "application/x-www-form-urlencoded"
        }).done(
            function (res){
                console.log(res)
            }
        ).fail(
            function (err){
                console.log(err)
            }
        )*/
        document.getElementById("leave-input").value = "right";
        document.getElementById("mySelect").value = "All";
        document.getElementById("msg").value = "我走辣"
        document.getElementById("submit-btn").click();
        document.getElementById("leave-input").value = null;
        document.getElementById("msg").value = null;
    }

    // 关闭浏览器默认给他的Session销毁掉
    window.addEventListener('beforeunload', leaveMsg);

    var length = 0;
    var textarea = document.getElementById('chat');
    function getXhr(){
        var username = "${name}"
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/api/accept', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var servletResponse = JSON.parse(xhr.responseText);
                    if(servletResponse!=null){
                        var newLength = servletResponse.length
                        for(var i=length;i<newLength;++i){
                            if(servletResponse[i].from==username||servletResponse[i].to==username||servletResponse[i].to=="All"){
                                textarea.value += servletResponse[i].from + "对"+ servletResponse[i].to + "说：" + servletResponse[i].msg+ "\n";
                            }
                        }
                        length = newLength;
                        textarea.scrollTop = textarea.scrollHeight;
                        console.log(servletResponse);
                    }
                } else {
                    console.error('请求出错');
                }
            }
        };
        xhr.send();
    }

    function getSelectionOptions(){
        var username = "${name}"
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/api/chatroom', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var servletResponse = JSON.parse(xhr.responseText);
                    console.log(servletResponse)
                    var select = document.getElementById('mySelect');
                    select.innerHTML = "";
                    for(let i = 0; i < servletResponse.length; i++) {
                        if(servletResponse[i]!=username){
                            select.innerHTML +="<option>"+servletResponse[i]+"</option>"
                        }
                    }
                    console.log("inner"+select.innerHTML)
                } else {
                    console.error('请求出错');
                }
            }
        };
        xhr.send();
    }

    setInterval(getXhr, 2000);
    setInterval(getSelectionOptions, 10000);
</script>
</body>
</html>
