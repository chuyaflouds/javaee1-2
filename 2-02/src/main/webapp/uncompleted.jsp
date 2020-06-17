<%@ page import="org.example.javaee.class01.model.Homework" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/6/17
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Uncompleted Homework</title>
</head>
<body>
<table  align="center" width="960" border="1" bgcolor="#ffebcd" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#cd5c5c" height="50">
        <td>作业编号</td>
        <td>作业标题</td>
        <td>作业内容</td>
        <td>发布时间</td>
        <td>提交</td>
    </tr>
    <%
        List<Homework> list=(List<Homework>)request.getAttribute("list");
        if(null==list||list.size()<=0){
            System.out.println("None data.");
        }else{
            for(Homework h:list){
    %>
    <tr align="center" height="30">
        <td><%=h.getId()%></td>
        <td><%=h.getTitle()%></td>
        <td><%=h.getContent()%></td>
        <td><%=h.getCreate_time()%></td>
        <td><a type="button" href="/GetHid?id=<%=h.getId()%>">提交</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
