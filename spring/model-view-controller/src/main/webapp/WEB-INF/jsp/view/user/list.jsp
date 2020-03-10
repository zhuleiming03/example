<%--
  Created by IntelliJ IDEA.
  User: fairy
  Date: 2020/3/6
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
</head>
<body>
<h2>Users</h2>
[<a href="<c:url value="/user/add" />">new user</a>]<br />
<br />
<c:forEach items="${userList}" var="user">
    ${user.name} (${user.userNo})
    [<a href="<c:url value="/user/edit/${user.userId}"/>">edit</a>]<br/>
</c:forEach>
</body>
</html>
