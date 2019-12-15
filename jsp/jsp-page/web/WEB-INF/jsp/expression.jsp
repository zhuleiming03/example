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
表达式中包含了一些简单的Java代码，可用于向客户端输出一些内容，它将把代码的返回值变量输出到客户端。<br>
因此可以在表达式中执行数学计算，因为数值结果是可以显示在客户端的。<br>
还可以调用一些返回字符串、数字或其他原生类型的方法，因为这些类型的返回值都是可显示的。<br>
事实上，任何赋值表达式的整个右侧都可以用在表达式中。<br>
表达式的作用域与脚本相同；如同脚本一样，表达式也将被复制到_jspService方法中。
<br><br>
example:
<%= "This is a expression!" %>
</body>
</html>
