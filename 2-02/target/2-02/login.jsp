<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/5/23
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%	request.setCharacterEncoding("GB18030");
//加上这一句解决的
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
     //存放下来菜单对应值的数组
    ArrayList nu = new ArrayList();
    nu.add("老师");
    nu.add("学生");
    %>

<html>
<head>
    <title>学生作业管理系统</title>
</head>
<body>
<form action="login" method="post">
    <select>
        <option value="student">学生</option>
        <option value="teacher">老师</option>
    </select>
    <select name=number>
        <%
            for (int i = 0; i < nu.size(); i++) {
                out.print("<option>" + nu.get(i) + "</option>");
            }
            %>
    </select>
    <label> ID：</label>
    <input type="text" name="id" placeholder="输入id" />
    <input type="submit" value="登录"/>
</form>
</body>

<%
    //取得提交的数字，并显示
    String n = request.getParameter("number");

%>
</html>
