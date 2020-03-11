<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<h4>1 在Bean中添加约束验证注解</h4>
<p>
    @NotNull 验证 <a href="<%=request.getContextPath()%>/user/">test</a>
</p>
<p>
    其他标准验证 <a href="<%=request.getContextPath()%>/teacher/">test</a>
</p>
<p>
    @Valid 递归验证 <a href="<%=request.getContextPath()%>/student/">test</a>
</p>
<h4>2 为方法验证配置 Spring Bean</h4>
<p>
    方法入参出参验证 <a href="<%=request.getContextPath()%>/student/get">test</a>
</p>
<p>
    控制器验证 <a href="<%=request.getContextPath()%>/message/show">test</a>
</p>
<h4>3 自定义约束验证</h4>
<p>
    @NotBlank @Email 验证 <a href="<%=request.getContextPath()%>/contact">test</a>
</p>
</body>
</html>
