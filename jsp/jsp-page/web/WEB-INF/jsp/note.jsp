<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%! String result = "执行结果："; %>

<ul>
    <li>XML注释</li>
    <!-- 这是一个xml注释,其中java代码继续执行 <%= result = result+9*9 %> -->

    <li>传统的Java行注释</li>
    <%!
        // 这是一个传统的Java行注释 会保留在jsp代码中，不会发送到浏览器中
        // 只能在声明和脚本中使用
    %>

    <li>传统的Java块注释</li>
    <%
        /*  这是一个传统的Java块注释
            会保留在jsp代码中
            不会发送到浏览器中
            只能在声明和脚本中使用*/
    %>

    <li>JSP注释</li>
    <%-- 这是一个JSP注释 不会被JSP解释器编译，更不会被发送到浏览器中 --%>
</ul>
Result:<%=result%>
</body>
</html>
