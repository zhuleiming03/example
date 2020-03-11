<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Message</title>
</head>
<body>
<h2>Create Message</h2>
<form:form method="post" modelAttribute="Message">
    <form:label path="id">ID</form:label><br />
    <form:input path="id" /><br />
    <form:errors path="id" cssClass="errors" /><br />

    <form:label path="info">Message</form:label><br />
    <form:input path="info" /><br />
    <form:errors path="info" cssClass="errors" /><br />

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
