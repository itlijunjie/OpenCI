<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sf:form modelAttribute="user" method="post">
		Username:<sf:input path="username"/><sf:errors path="username"></sf:errors><br/>
		Password:<sf:password path="password"/><sf:errors path="password"></sf:errors><br/>
		Nikename:<sf:input path="nickname"/><sf:errors path="nickname"></sf:errors><br/>
		Email:<sf:input path="email"/><sf:errors path="email"></sf:errors><br/>
		<input type="submit" value="修改用户"/>
	</sf:form>
</body>
</html>