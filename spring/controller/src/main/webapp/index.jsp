<%@ page contentType="text/html;charset=gb2312" %>
<html>
<body>
<h2>Hello World!</h2>

<ul>
    <li>URL限制
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/demo/index">路径：/demo/index</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/teacher">路径：/demo/school/teacher</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/student/t">路径：/demo/school/student/t</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/student/tom">路径：/demo/school/student/tom</a>
            </li>
        </ul>
    </li>
    <li>HTTP请求限制
        <a href="<%=request.getContextPath()%>/demo/customer/add">实例</a>
    </li>
    <li>请求参数限制
        <a href="<%=request.getContextPath()%>/demo/customer/query?id=101&level=3">实例</a>
    </li>
    <li>请求头限制</li>
    <li>内容类型限制</li>
</ul>

</body>
</html>
