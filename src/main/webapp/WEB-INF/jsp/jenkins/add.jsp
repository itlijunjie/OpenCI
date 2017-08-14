<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户</title>
</head>
<body>
<sf:form modelAttribute="jenkins" method="post">
    Jenkins服务器url:<sf:input path="url"/><sf:errors path="url"></sf:errors><br/>
    服务器名称:<sf:input path="serverName"/><sf:errors path="serverName"></sf:errors><br/>
    服务器登录用户名:<sf:input path="username"/><sf:errors path="username"></sf:errors><br/>
    服务器登录密码:<sf:input path="password"/><sf:errors path="password"></sf:errors><br/>
    Jenkins服务器描述:<sf:textarea path="description"/><sf:errors path="description"></sf:errors><br/>
    <input type="submit" value="添加Jenkins服务器"/>
</sf:form>
</body>
</html>