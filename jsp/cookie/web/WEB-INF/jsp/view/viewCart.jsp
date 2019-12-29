<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/29
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <title>View Cart</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
</head>
<body>
<h2>View Cart</h2>
<a href="<%=basePath%>/shop" >Product List</a><br/><br/>
<a href="<%=basePath%>/shop?action=emptyCart" >Empty Cart</a><br/><br/>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, String> products =
            (Map<Integer, String>) request.getAttribute("products");
    @SuppressWarnings("unchecked")
    Map<Integer, Integer> cart =
            (Map<Integer, Integer>) session.getAttribute("cart");

    if (cart == null || cart.size() == 0)
        out.println("Your cart is empty.");
    else {
        for (int id : cart.keySet()) {
            out.println(products.get(id) + " (count: " + cart.get(id) +
                    ")<br />");
        }
    }
%>
</body>
</html>
