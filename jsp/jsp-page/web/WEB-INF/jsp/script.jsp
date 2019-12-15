<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="example.jsp.Compute"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
如同声明一样，脚本中也包含了Java代码。<br>
不过脚本有着不同的作用域。<br>
声明中的代码将在转换时复制到JSP Servlet类的主体中，并且它们可用于声明某些字段、类型或方法，而脚本则将被复制到_jspService方法的主体中。<br>
该方法中的所有局部变量都可以在脚本中使用，任何在该方法体中合法的代码在脚本中也是合法的。<br>
所以，在脚本中可以定义局部变量而不是实例字段。<br>
另外还可以使用条件语句、操作对象和执行数学计算，这些在声明中都是无法完成的。<br>
我们甚至可以在脚本中定义类（听起来很奇怪，但在Java的方法中定义类是合法的），但这些类只在_jspService方法中有效。<br>
声明中定义的类、方法或者变量都可以在脚本中使用，但脚本中定义的类或变量不能在声明中使用。
</body>
</html>

<%--声明 result--%>
<%! private int result = 11; %>

<%--脚本 执行计算操作--%>
<%
    result = Compute.add(result);
    String content = Compute.TITLE + result;
%>

<%--表达式 显示计算结果--%>
<br><br>result: <%= content%>