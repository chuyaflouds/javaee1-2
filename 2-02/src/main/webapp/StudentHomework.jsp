<%@ page import="java.util.List" %>
<%@ page import="org.example.javaee.class01.model.StudentHomework" %>
<%@ page import="org.example.javaee.class01.jdbc.StudentHomeworkjdbc" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/3/12
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Homework</title>
</head>
<body>
<table align="center" width="960" border="1" bgcolor="#ffebcd" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#cd5c5c" height="50">
        <td>ID</td>
        <td>学生学号</td>
        <td>作业编号</td>
        <td>作业标题</td>
        <td>作业内容</td>
        <td>创建时间</td>
    </tr>
    <%
        List<StudentHomework> list = (List<StudentHomework>) StudentHomeworkjdbc.selectAll();
        if(null==list||list.size()<=0){
            System.out.println("None data.");
        }else{
            for(StudentHomework sh:list){
    %>
    <tr align="center" height="30">
        <td><%=sh.getId()%></td>
        <td><%=sh.getStudentId()%></td>
        <td><%=sh.getHomeworkId()%></td>
        <td><%=sh.getHomeworkTitle()%></td>
        <td><%=sh.getHomeworkContent()%></td>
        <td><%=sh.getCreateTime()%></td>

    </tr>
    <%
            }
        }
    %>

</table>
</body>
</html>
