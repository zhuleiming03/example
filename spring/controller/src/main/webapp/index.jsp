<%@ page contentType="text/html;charset=gb2312" %>
<html>
<body>
<h2>Hello World!</h2>

<ul>
    <li>URL����
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/demo/index">·����/demo/index</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/teacher">·����/demo/school/teacher</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/student/t">·����/demo/school/student/t</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/demo/school/student/tom">·����/demo/school/student/tom</a>
            </li>
        </ul>
    </li>
    <li>HTTP��������
        <a href="<%=request.getContextPath()%>/demo/customer/add">ʵ��</a>
    </li>
    <li>�����������
        <a href="<%=request.getContextPath()%>/demo/customer/query?id=101&level=3">ʵ��</a>
    </li>
    <li>����ͷ����</li>
    <li>������������</li>
</ul>

</body>
</html>
