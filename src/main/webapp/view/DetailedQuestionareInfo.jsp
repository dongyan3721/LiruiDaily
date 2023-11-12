<%@ page import="java.util.HashMap" %>
<%@ page import="com.liruisecond.liruisecond.entity.FormContent" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: 29145
  Date: 2023/10/21
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>问卷详情</title>
</head>

<body>
<%--${content.get("000")}--%>
<table>
    <tr>
        <td>填写人数</td>
        <td>${total}</td>
    </tr>
    <tr>
        <td>男性数量</td>
        <td>${man}</td>
    </tr>
    <tr>
        <td>女性数量</td>
        <td>${women}</td>
    </tr>
    <tr>
        <td>读书爱好人次</td>
        <td>${read}</td>
    </tr>
    <tr>
        <td>旅游爱好人次</td>
        <td>${travel}</td>
    </tr>
    <tr>
        <td>体育爱好人次</td>
        <td>${pe}</td>
    </tr>
    <tr>
        <td>游戏爱好人次</td>
        <td>${game}</td>
    </tr>
</table>
<h2>填写明细</h2>
<%
    HashMap<String, FormContent> content = (HashMap<String, FormContent>) session.getAttribute("content");
    Set<String> keySet = content.keySet();
    for (String key : keySet) {
        FormContent formContent = content.get(key);

%>
<a href="#" class="detail-a-label">
    填写人姓名：<%=formContent.getName()%>
</a><br/>
<%
    }
%>
<script>
    var links = document.getElementsByClassName("detail-a-label");
    var content = ${contStr};
    for (var i = 0; i < links.length; i++) {
        links[i].addEventListener("click", function(){
            var name = this.innerText;
            var obj = content[name.replace("填写人姓名：", "")];
            var p1 = document.createElement("p");
            var p2 = document.createElement("p");
            var p3 = document.createElement("p");
            var p4 = document.createElement("p");

            // 添加文本内容
            p1.textContent = "姓名"+name;
            p2.textContent = "性别"+obj["gender"];
            p3.textContent = "年龄"+obj["age"];
            p4.textContent = "爱好"+obj["hobbies"];

            // 创建展示具体信息的p标签
            var div = document.createElement("div");
            div.appendChild(p1);
            div.appendChild(p2);
            div.appendChild(p3);
            div.appendChild(p4);

            // 创建关闭按钮
            var closeButton = document.createElement("button");
            closeButton.textContent = "关闭";
            div.appendChild(closeButton);


            // 创建对话框
            var dialog = document.createElement("dialog");
            dialog.appendChild(div);

            // 添加按钮单击关闭对话框事件
            closeButton.onclick = function() {
                dialog.close();
            };

            // 将对话框添加到文档中
            document.body.appendChild(dialog);

            // 打开对话框
            dialog.showModal();

            console.log(content)
        });
    }
</script>
</body>
</html>
