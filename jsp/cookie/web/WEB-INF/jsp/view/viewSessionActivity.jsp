<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/29
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector, example.jsp.po.PageVisit, java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Session Activity Tracker</title>
    <%
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath();
    %>
    <base href="<%=basePath%>">
    <%!
        private static String toString(long timeInterval)
        {
            if(timeInterval < 1_000)
                return "less than one second";
            if(timeInterval < 60_000)
                return (timeInterval / 1_000) + " seconds";
            return "about " + (timeInterval / 60_000) + " minutes";
        }
    %>
    <%
        SimpleDateFormat f = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    %>
</head>
<body>
<h2>Session Properties</h2>
Session ID: <%= session.getId() %><br />
Session is new: <%= session.isNew() %><br />
Session created: <%= f.format(new Date(session.getCreationTime()))%><br />

<h2>Page Activity This Session</h2>
<%
    @SuppressWarnings("unchecked")
    Vector<PageVisit> visits =
            (Vector<PageVisit>)session.getAttribute("activity");

    for(PageVisit visit : visits)
    {
        out.print(visit.getRequest());
        if(visit.getIpAddress() != null)
            out.print(" from IP " + visit.getIpAddress().getHostAddress());
        out.print(" (" + f.format(new Date(visit.getEnteredTimestamp())));
        if(visit.getLeftTimestamp() != null)
        {
            out.print(", stayed for " + toString(
                    visit.getLeftTimestamp() - visit.getEnteredTimestamp()
            ));
        }
        out.println(")<br />");
    }
%>
</body>
</html>
