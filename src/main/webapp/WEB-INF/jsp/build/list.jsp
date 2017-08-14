<%@page import="com.itlijunjie.openci.util.ConstUtil" %>
<%@page import="com.itlijunjie.openci.util.enums.BuildStatusEnum" %>
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
</head>
<body>
<div class="page">
    <table>
        <thead>
        <tr>
            <td>Build ID</td>
            <td>Build buildNumber</td>
            <td>Build serverId</td>
            <td>Build appId</td>
            <td>Build status</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="build" items="${builds.list }">
            <tr>
                <td>${build.id }</td>
                <td>${build.buildNumber }</td>
                <td>${build.serverId }</td>
                <td>${build.appId }</td>
                <td>${BuildStatusEnum.getName(build.status)}</td>ProductSkuStatus.New.getValue()
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:choose>
        <c:when test="${builds.havePre }">
            <a href="builds?pageNo=${builds.curPage-1 }">上一页</a>
        </c:when>
        <c:otherwise>
            上一页
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${builds.haveNext }">
            <a href="builds?pageNo=${builds.curPage+1 }">下一页</a>
        </c:when>
        <c:otherwise>
            下一页
        </c:otherwise>
    </c:choose>
    当前第${builds.curPage }页
    共<span>${builds.pageTotalNum }</span>页
    跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
</div>
</body>
</html>