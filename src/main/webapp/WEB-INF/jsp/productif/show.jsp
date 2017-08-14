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
	<table width="100%" border="1px" align="center">
		<tr>
			<td>接口名称:</td>
			<td width="85%">${productInterface.ifid }</td>
		</tr>
		<tr>
			<td>接口名称:</td>
			<td>${productInterface.ifname }</td>
		</tr>
		<tr>
			<td>接口描述:</td>
			<td>${productInterface.ifdescription }</td>
		</tr>
		<tr>
			<td>返回结果:</td>
			<td>${productInterface.ifresule }</td>
		</tr>
		<tr>
			<td>访问路径参数:</td>
			<td>${productInterface.ifparameter }</td>
		</tr>
		<tr>
			<td>项目名称:</td>
			<td>${productInterface.productname }</td>
		</tr>
	</table>
</body>
</html>