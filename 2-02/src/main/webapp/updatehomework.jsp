<%@ page import="org.example.javaee.class01.model.StudentHomework" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/17
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Homework</title>
</head>
<body>
<table  align="center" width="960" border="1" bgcolor="#ffebcd" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#cd5c5c" height="50">
        <td>ID</td>
        <td>作业编号</td>
        <td>作业标题</td>
        <td>作业内容</td>
        <td>我的答案</td>
        <td>批改结果</td>
        <td>修订</td>
    </tr>
    <%
        List<StudentHomework> list=(List<StudentHomework>)request.getAttribute("list");
        if(null==list||list.size()<=0){
            System.out.println("None data.");
        }else{
            for(StudentHomework sh:list){
    %>
    <tr align="center" height="30">
        <td><%=sh.getId()%></td>
        <td><%=sh.getHomework_id()%></td>
        <td><%=sh.getHomework_title()%></td>
        <td><%=sh.getHomework_content()%></td>
        <td><%=sh.getSubmit_content()%></td>
        <td><%=sh.getReview_content()%></td>
        <td><a type="button" href="/GetNSHid?id=<%=sh.getId()%>">修订</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
