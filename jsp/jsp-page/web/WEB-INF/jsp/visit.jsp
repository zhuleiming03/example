<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/15
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
jsp页面访问方式<br/>
WEB-INF下的文件不能通过客户端直接访问， 只能通过Servlet访问
<ul>
    <li>通过配置web.xml来访问</li>
    <li>1、在web中新增 servlet 节点，分别配置 servlet-name 和 jsp-file</li>
    <li>2、在web中新增 servlet-mapping 节点，分别配置 servlet-name 和 url-pattern</li>
    <li>3、在访问页面（index.jsp）设置 basePath 值</li>
    <li>4、实现超链接访问目标jsp页面</li>
</ul>
<ul>
    <li>通过实现servlet来访问</li>
    <li>1、新建一个servlet</li>
    <li>2、实现servlet的跳转方法</li>
    <li>3、在访问页面（index.jsp）实现访问servlet的方法</li>
</ul>
</body>
</html>
