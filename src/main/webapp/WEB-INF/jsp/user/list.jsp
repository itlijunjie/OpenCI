<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function tiaozhuan(){
	var page = document.getElementById("tiaozhuanye").value;
	if(page>=1 && page <= ${users.pageTotalNum }){
		window.location.href='users?pageNo='+page;
	}else{
		alert("请输入正确的页码！");
		return false;
	}
}
</script>
</head>
<body>
<a href="add">添加</a>
	<table width="100%" border="1px" align="center">
		<tr>
			<td>用户标识</td>
			<td>用户姓名</td>
			<td>用户昵称</td>
			<td>用户密码</td>
			<td>用户邮件</td>
			<td>操作</td>
		</tr>
		<c:forEach var="user" items="${users.list }">
			<tr>
				<td>${user.id }</td>
				<td><a href="${user.id }">${user.username }</a></td>
				<td>${user.nickname }</td>
				<td>${user.password }</td>
				<td>${user.email }</td>
				<td><a href="${user.id }/update">修改</a><a href="${user.id }/delete">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	
	
	<c:choose>  
    	<c:when test="${users.havePre }">
	        <a href="users?pageNo=${users.curPage-1 }">上一页</a>
	    </c:when> 
    	<c:otherwise>  
    		上一页  
    	</c:otherwise>  
    </c:choose>
    <c:choose>  
    	<c:when test="${users.haveNext }">
	        <a href="users?pageNo=${users.curPage+1 }">下一页</a>
	    </c:when> 
    	<c:otherwise>  
    		下一页  
    	</c:otherwise>  
    </c:choose>
             当前第${users.curPage }页
	共<span>${users.pageTotalNum }</span>页
	跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
</body>
</html>