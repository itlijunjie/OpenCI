<%@page import="com.itlijunjie.openci.util.ConstUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        function showLink(link){
            alert(link);
        }
        function tiaozhuan() {
            var page = document.getElementById("tiaozhuanye").value;
            if (page >= 1 && page <= ${reources.pageTotalNum }) {
                window.location.href = 'reources?pageNo=' + page;
            } else {
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
        <td>资源ID</td>
        <td>资源描述</td>
        <td>操作</td>
    </tr>
    <c:forEach var="reource" items="${reources.list }">
        <tr>
            <td>${reource.id }</td>
            <td>${reource.description }</td>
            <td>
                <a href="<%=ConstUtil.DOWNLOAD_PATH%>/${reource.name }" target="_blank">查看</a>
                <a href="${reource.id }/delete">删除</a></br>
                <input type="button" onclick="showLink('<%=ConstUtil.DOWNLOAD_PATH%>/${reource.name }');" value="查看链接"/><br/>
            </td>
        </tr>
    </c:forEach>
</table>


<c:choose>
    <c:when test="${reources.havePre }">
        <a href="reources?pageNo=${reources.curPage-1 }">上一页</a>
    </c:when>
    <c:otherwise>
        上一页
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${reources.haveNext }">
        <a href="reources?pageNo=${reources.curPage+1 }">下一页</a>
    </c:when>
    <c:otherwise>
        下一页
    </c:otherwise>
</c:choose>
当前第${reources.curPage }页
共<span>${reources.pageTotalNum }</span>页
跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;使用说明：如服务器部署的IP地址是<%=ConstUtil.IP %><br/>
&nbsp;&nbsp;&nbsp;&nbsp;资源描述：对该资源的描述，在添加的时候不是必填，<br/>
&nbsp;&nbsp;&nbsp;&nbsp;上传url：<%=ConstUtil.SERVER_RESOURCES %>/upload(为固定格式),资源名称为file<br/>
</body>
</html>