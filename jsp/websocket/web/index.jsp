<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/31
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>websocket</title>
</head>

<body>
<h2>Hello World!</h2>
<div>
    这是个简单的 websocket 交互<br>
    前端可以给后台发送消息并及时得到响应<br>
    后台也可以定时给前端反馈结果数据
</div>
<br>
<div>
    <span>sessionId:</span>
    <%
        HttpSession s = request.getSession();
        out.println(s.getId());
    %>
</div>
<br>
<div>
    <input id="sessionId" type="hidden" value="<%=session.getId() %>"/>
    <input id="text" type="text"/>
    <button onclick="send()">发送消息</button>
</div>
<br>
<div>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
</div>
<br>
---------------------------------------------------
<div id="message"></div>
 
</body>

<script type="text/javascript">
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket_Web_exploded/back");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws:////localhost:8080/websocket_Web_exploded/back");
    } else {
        websocket = new SockJS("localhost:8080/websocket_Web_exploded/back");
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("WebSocket 连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        setMessageInnerHTML("WebSocket 连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket 连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>

</html>
