<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <li><a href="<%=basePath%>/visit">jsp页面访问</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=instructions">指令</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=declare">声明</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=script">脚本</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=expression">表达式</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=note">注释</a></li>
    <li><a href="<%=basePath%>/visit/jsp?action=user">实例</a></li>
</ul>

</body>
</html>
