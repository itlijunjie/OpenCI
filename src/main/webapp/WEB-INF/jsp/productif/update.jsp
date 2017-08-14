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

	<sf:form modelAttribute="productInterface" method="post">
		<table>
			<tr>
				<td>接口名称:</td>
				<td><sf:input path="ifname" />
					<sf:errors path="ifname"></sf:errors></td>
			</tr>
			<tr>
				<td>接口描述:</td>
				<td><sf:textarea path="ifdescription" />
					<sf:errors path="ifdescription"></sf:errors></td>
			</tr>
			<tr>
				<td>返回结果:</td>
				<td><sf:textarea path="ifresule" />
					<sf:errors path="ifresule"></sf:errors></td>
			</tr>
			<tr>
				<td>访问路径参数:</td>
				<td><sf:input path="ifparameter" />
					<sf:errors path="ifparameter"></sf:errors></td>
			</tr>
			<tr>
				<td>项目名称:</td>
				<td><sf:input path="productname" />
					<sf:errors path="productname"></sf:errors></td>
			</tr>
			<tr>
				<td>提交：</td>
				<td><input type="submit" value="提交修改" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>