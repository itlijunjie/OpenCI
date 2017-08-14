<%@page import="com.itlijunjie.openci.util.ConstUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="<%=ConstUtil.SERVER_RESOURCES%>/css/page.css"/>
    <script type="text/javascript"
            src="<%=ConstUtil.SERVER_RESOURCES%>/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript">
        function tiaozhuan(){
            var page = document.getElementById("tiaozhuanye").value;
            if(page>=1 && page <= ${apps.pageTotalNum }){
                window.location.href='apps?pageNo='+page;
            }else{
                alert("请输入正确的页码！");
                return false;
            }
        }
        function startBuild(appId) {
            $.post('build/' + appId ,function (data) {
                alert(data);
            });
        }
        function showBuilds(appId) {
			console.log(appId)
		}
    </script>

</head>
<body>
<div class="page">
    <a class="add-btn" href="add">添加</a>
    <table>
        <thead>
        <tr>
            <td>App标识</td>
            <td>App名称</td>
            <td>服务器名称</td>
            <td>Item名称</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${apps.list }">
            <tr>
                <td>${app.id }</td>
                <td><a href="${app.id }">${app.name }</a></td>
                <td>${app.serverId }</td>
                <td>${app.itemName }</td>
                <td>
                    <a href="${app.id }/update">修改</a>
                    <a href="${app.id }/delete">删除</a>
                    <a href="<c:url value="/build/builds?pageNo=1"/>">构建列表</a>
                    <button onclick="startBuild(${app.id })">开始构建</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:choose>
        <c:when test="${apps.havePre }">
            <a href="apps?pageNo=${apps.curPage-1 }">上一页</a>
        </c:when>
        <c:otherwise>
            上一页
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${apps.haveNext }">
            <a href="apps?pageNo=${apps.curPage+1 }">下一页</a>
        </c:when>
        <c:otherwise>
            下一页
        </c:otherwise>
    </c:choose>
    当前第${apps.curPage }页
    共<span>${apps.pageTotalNum }</span>页
    跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
</div>
</body>
</html>