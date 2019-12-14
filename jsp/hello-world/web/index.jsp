<%--
  Created by IntelliJ IDEA.
  User: zhuleiming
  Date: 2019/12/14
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
创建步骤：
<br/>1、修改 pom -> 添加javax.servlet-api 依赖
<br/>2、修改 Project Structure -> Modules 新增 web (Web Module Deployment Descriptor) -> Create Artifact
<br/>3、web 添加 index.jsp 文件
<br/>4、编辑 Configuration -> 新增 Tomcat -> local -> Deployment 添加 Artifact -> Apply
<br/>
<br/>打包站点：
<br/>1、pom 文件新增 packaging 和 build 节点
<br/>2、通过 maven 的 package 打包
<br/>3、将war包放入 apache 的 webapps 中运行

</body>
</html>
