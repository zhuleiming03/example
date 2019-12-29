<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/29
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>Product List</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
</head>
<body>
<h2>Product List</h2>
<a href="<%=basePath%>/shop?action=viewCart">View Cart</a><br/><br/>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, String> products =
            (Map<Integer, String>) request.getAttribute("products");

    for (int id : products.keySet()) {
%> <%= products.get(id) %>
<a href="<%=basePath%>/shop?action=addToCart&productId=<%= Integer.toString(id) %>">
    Add
</a><br/><%
    }
%>
</body>
</html>
