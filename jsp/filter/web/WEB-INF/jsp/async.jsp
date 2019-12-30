<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/30
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>async</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
</head>
<body>
<% System.out.println("In async.jsp."); %>

<ul>
    Async JSP
    <li>request->Filter->Servlet->thread</li>
    <li>thread 是异步的,所以 Servlet 直接回调</li>
    <li>Servlet->Filter->response</li>
    <li>thread->page</li>
    <li><a href="<%=basePath%>/async">startAsync 使用封装后的参数</a></li>
    <li><a href="<%=basePath%>/async?unwrap=1">startAsync 使用原始参数</a></li>
</ul>
</body>
</html>
