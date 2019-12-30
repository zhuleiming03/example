<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/30
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>order</title>
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
    filter 可以通过URL和Servlet两种方式匹配
    <li><a href="<%=basePath%>/article?page=order">Servlet：访问本页</a></li>
</ul>
</body>
</html>
