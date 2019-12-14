<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/14
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>servlet</title>
</head>
<body>
<ul>
    <li>annotationServlet</li>
    <li>用注解的方式实现一个 Servlet ，并自定义其初始化方法和销毁方法</li>
    <li>1、新建一个 Servlet </li>
    <li>2、新增 urlPatterns 参数，赋予 get 方法的路径，实现 get 方法</li>
    <li>3、分别实现 init 和 destroy 方法</li>
</ul>

<ul>
    <li>xmlServlet</li>
    <li>用描述符的方式实现一个 Servlet ，</li>
    <li>1、新建一个 Servlet 并去掉 @WebServlet 注解</li>
    <li>2、实现 Servlet 的 get 方法 </li>
    <li>3、配置 web.xml 的 servlet 和 servlet-mapping 节点</li>
    <li>servlet 和 servlet-mapping 的 servlet-name 要保持一致 </li>
    <li>多个 url（/first;/one） 可以匹配到同一个 servlet（FirstServlet） </li>
    <li>多个 servlet（FirstServlet;secondServlet） 可以匹配到同一个类（xmlServlet） </li>
</ul>

<ul>
    <li>userServlet,fruitServlet</li>
    <li> get 和 post 的实例</li>
</ul>

<ul>
    <li>contextServlet</li>
    <li>上下文初始化参数，为 web 容器所有 servlet 配置参数</li>
    <li>1、配置 web.xml 的 context-param 节点</li>
    <li>2、定义一个 contextServlet，实现 get 方法，获取配置 dateBase 的值</li>
</ul>

<ul>
    <li>configXmlServlet</li>
    <li>通过 xml 为特定 servlet 配置参数</li>
    <li>1、用描述符的方式实现一个 Servlet</li>
    <li>2、配置 servlet 节点下的 init-param 节点</li>
    <li>3、实现 get 方法，调用配置节点中的值</li>
</ul>

<ul>
    <li>configXmlServlet</li>
    <li>通过 annotation 为特定 servlet 配置参数</li>
    <li>1、用注解的方式实现一个 Servlet</li>
    <li>2、配置 initParams 注解</li>
    <li>3、实现 get 方法，调用配置节点中的值</li>
</ul>


</body>
</html>
