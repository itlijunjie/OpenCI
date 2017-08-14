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
<sf:form modelAttribute="app" method="post">
    App Name:<sf:input path="name"/><sf:errors path="name"></sf:errors><br/>
    Server Id:<sf:input path="serverId"/><sf:errors path="serverId"></sf:errors><br/>
    Item Name:<sf:input path="itemName"/><sf:errors path="itemName"></sf:errors><br/>
    <input type="submit" value="添加App"/>
</sf:form>
</body>
</html>