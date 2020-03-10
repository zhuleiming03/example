<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<p>
    <a href="<%=request.getContextPath()%>/home/">重定向视图实例</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/home/dashboard">显示视图实例</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/user/current">隐式视图实例</a>
</p>
<p>
    <a href="<%=request.getContextPath()%>/user/list">表单实例</a>
</p>
</body>
</html>
