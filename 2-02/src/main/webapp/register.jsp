<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/17
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="Register" method="post">
    <p>注册:</p>
    <div>
        <label> ID：</label>
        <input type="text" name="id" placeholder="ID" />

        <label> Name：</label>

        <input type="text" name="n" placeholder="Name" />

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
        <button type="submit">确认</button>
    </div>
</form>
</body>
</html>
