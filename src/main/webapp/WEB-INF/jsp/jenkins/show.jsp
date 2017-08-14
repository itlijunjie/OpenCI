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
		Jenkins服务器url:${ jenkins.url }<br/>
		服务器名称:${ jenkins.serverName }<br/>
		服务器登录用户名:${ jenkins.username }<br/>
		服务器登录密码:${ jenkins.password }<br/>
		Jenkins服务器描述:${ jenkins.description }<br/>
</body>
</html>