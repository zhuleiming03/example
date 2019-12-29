<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/29
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cookie</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
</head>
<body>
<ul>
    <li><a href="<%=basePath%>/shop">cart cookie test</a></li>
    <li><a href="<%=basePath%>/do">object cookie test</a></li>
</ul>
</body>
</html>
