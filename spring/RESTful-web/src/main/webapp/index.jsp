<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<p>
    Web访问 <a href="<%=request.getContextPath()%>/index">click</a>
</p>
<p>
    <ul>Rest访问
        <li>Json get url: http://localhost:8080/RESTful_web_war/rest/json</li>
        <li>Json post url: http://localhost:8080/RESTful_web_war/rest/json?userId=909</li>
        <li>xml get url: http://localhost:8080/RESTful_web_war/rest/xml</li>
    </ul>
</p>
</body>
</html>
