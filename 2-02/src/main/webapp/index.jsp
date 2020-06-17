<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/5
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生作业管理系统</title>
</head>
<body>
<form action="Login" method="post">
    <p>作业管理系统:</p>
    <div>
        <label> ID：</label>
        <input type="text" name="id" placeholder="ID" />

        <label> Password：</label>
        <input type="text" name="pw" placeholder="Password" />

        <input type="radio" id="contactChoice1"
               name="role" value="student" checked>
        <label for="contactChoice1">学生</label>

        <input type="radio" id="contactChoice2"
               name="role" value="teacher">
        <label for="contactChoice2">老师</label>
    </div>
    <div>
        <br>
        <button type="submit">登录</button>
    </div>
    <div>
        <br>
        <button onclick="window.open('register.jsp')">注册</button>
    </div>
</form>
</body>
</html>
