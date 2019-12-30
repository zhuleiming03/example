<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/30
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>filter</title>
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
    filter
    <li><a href="<%=basePath%>/article">使用部署描述符</a></li>
    <li><a href="<%=basePath%>/article">使用注解</a></li>
    <li><a href="<%=basePath%>/article">使用编程式配置</a></li>
    <li><a href="<%=basePath%>/order/test/page">过滤器的加载顺序</a></li>
    <li><a href="<%=basePath%>/async">异步过滤器</a></li>
    <li><a href="<%=basePath%>/log">日志过滤器实例</a></li>
</ul>
</body>
</html>
