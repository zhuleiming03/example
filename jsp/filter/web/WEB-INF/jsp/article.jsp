<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/30
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>article</title>
</head>
<body>
<ul>
    使用部署描述符
    <li>1、新建 XmlFilter 类继承 Filter，并实现 destroy、init、doFilter</li>
    <li>2、配置 web.xml 的 filter 和 filter-mapping 配置项</li>
</ul>

<ul>
    使用注解
    <li>1、新建 AnnotateFilter 过滤器，并实现 destroy、init、doFilter</li>
    <li>2、配置 urlPatterns 和 servletNames 注解</li>
    <li>注：注解方式在JAVA EE 8前不能对过滤器排序</li>
</ul>

<ul>
    使用编程式配置
    <li>1、新建 CodeFilter 类继承 Filter，并实现 destroy、init、doFilter</li>
    <li>2、新建 CodeListener 监听器，并实现 contextInitialized</li>
</ul>

</body>
</html>
