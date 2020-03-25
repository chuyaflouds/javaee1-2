<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/10
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add StudentHomework</title>
</head>
<body>
<form action="addStudentHomework" method="post">
    <label> Student ID：</label>
    <input type="text" name="student_id" placeholder="Student ID" />
    <label> Homework ID：</label>
    <input type="text" name="homework_id" placeholder="Homework ID" />
    <input type="submit" value="确认"/>

</form>
</body>
</html>
