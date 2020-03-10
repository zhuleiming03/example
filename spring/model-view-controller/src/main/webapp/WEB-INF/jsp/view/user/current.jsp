<%--
  Created by IntelliJ IDEA.
  User: fairy
  Date: 2020/3/5
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"  %>
<!DOCTYPE html>
<html>
<head>
    <title>User Home</title>
</head>
<body>
ID: ${currentUser.userId}<br />
Username: ${currentUser.userNo}<br />
Name: ${currentUser.name}<br />
<a href="<%=request.getContextPath()%>/">主页</a>
</body>
</html>