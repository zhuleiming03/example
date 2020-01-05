<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%--pageEncoding 指定Jsp所用的字符编码--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--使用的是哪种语言指令--%>
<%@ page language="java" %>
<%--导入类指令--%>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>指令用于
    <li>修改页面属性
        <ul>
            <li>pageEncoding</li>
            <li>session</li>
            <li>isELIgnored</li>
            <li>buffer和autoFlush</li>
            <li>errorPage</li>
            <li>isErrorPage</li>
            <li>isThreadSafe</li>
            <li>extends</li>
        </ul>
    </li>
    <li>
        包含其他JSP<br>
        <%@include file="subpage.jsp"%>
    </li>
    <li>包含标签库（taglib）</li>
</ul>
</body>
</html>
