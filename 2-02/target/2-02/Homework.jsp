<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/10
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Homework</title>
</head>
<body>
    <form action="AddHomework" method="post">
    <label> Title：</label>
    <input type="text" name="title" placeholder="输入title" />
    <label> Content：</label>
    <input type="text" name="content" placeholder="输入content" />
    <input type="submit" value="确认"/>
    </form>
</body>
</html>
