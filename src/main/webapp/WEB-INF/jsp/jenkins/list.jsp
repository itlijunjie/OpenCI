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
	if(page>=1 && page <= ${jenkinses.pageTotalNum }){
		window.location.href='jenkinses?pageNo='+page;
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
			<td>Jenkins服务器标识</td>
			<td>服务器URL</td>
			<td>服务器名称</td>
			<td>登录用户名</td>
			<td>登录密码</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="j" items="${jenkinses.list }">
			<tr>
				<td>${j.id }</td>
				<td><a href="${j.id }">${j.url }</a></td>
				<td><a href="${j.id }">${j.serverName }</a></td>
				<td>${j.username }</td>
				<td>${j.password }</td>
				<td>${j.description }</td>
				<td><a href="${j.id }/update">修改</a><a href="${j.id }/delete">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	
	
	<c:choose>  
    	<c:when test="${jenkinses.havePre }">
	        <a href="jenkinses?pageNo=${jenkinses.curPage-1 }">上一页</a>
	    </c:when> 
    	<c:otherwise>  
    		上一页  
    	</c:otherwise>  
    </c:choose>
    <c:choose>  
    	<c:when test="${jenkinses.haveNext }">
	        <a href="jenkinses?pageNo=${jenkinses.curPage+1 }">下一页</a>
	    </c:when> 
    	<c:otherwise>  
    		下一页  
    	</c:otherwise>  
    </c:choose>
             当前第${jenkinses.curPage }页
	共<span>${jenkinses.pageTotalNum }</span>页
	跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
</body>
</html>