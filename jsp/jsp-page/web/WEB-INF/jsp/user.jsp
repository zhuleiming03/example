<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    String user = "Guest";
    String[] users = request.getParameterValues("user");

    if (users != null && users.length > 0) {
        user = users[0];
    }
%>

<html>
<head>
    <title>user</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
</head>
<body>
Hello,<%= user %> !
<br><br>
<form action="<%=basePath%>/visit/jsp" method="post">
    Enter your name :
    <input type="text" name="user"/><br>
    <input type="submit" value="Submit"/>
</form>


</body>
</html>
