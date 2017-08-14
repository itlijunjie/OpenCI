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
        function showLink(link) {
            alert(link);
        }

        function tiaozhuan() {
            var page = document.getElementById("tiaozhuanye").value;
            if (page >= 1 && page <= ${pis.pageTotalNum }) {
                window.location.href = 'productifs?pageNo=' + page;
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
        <td>接口标识</td>
        <td>接口名称</td>
        <td>接口描述</td>
        <td width="50%">返回结果</td>
        <td>访问路径参数</td>
        <td>项目名称</td>
        <td>操作</td>
    </tr>
    <c:forEach var="pi" items="${pis.list }">
        <tr>
            <td>${pi.ifid }</td>
            <td><a href="${pi.ifid }">${pi.ifname }</a></td>
            <td>${pi.ifdescription }</td>
            <td><textarea disabled="disabled" rows="5" style="width:99%">${pi.ifresule }</textarea></td>
            <td>${pi.ifparameter }</td>
            <td>${pi.productname }</td>
            <td>
                <a href="${pi.ifid }/update">修改</a>||
                <a href="${pi.ifid }/delete">删除</a><br/>
                <a href="<%=ConstUtil.JSON_TEST_PATH %>/${pi.productname }/${pi.ifparameter }">测试</a>||
                <input type="button"
                       onclick="showLink('<%=ConstUtil.JSON_TEST_PATH %>/${pi.productname }/${pi.ifparameter }');"
                       value="查看链接"/><br/>
            </td>
        </tr>
    </c:forEach>
</table>

<c:choose>
    <c:when test="${pis.havePre }">
        <a href="productifs?pageNo=${pis.curPage-1 }">上一页</a>
    </c:when>
    <c:otherwise>
        上一页
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${pis.haveNext }">
        <a href="productifs?pageNo=${pis.curPage+1 }">下一页</a>
    </c:when>
    <c:otherwise>
        下一页
    </c:otherwise>
</c:choose>
当前第${pis.curPage }页
共<span>${pis.pageTotalNum }</span>页
跳转到<input id="tiaozhuanye" type="text"/>页<input type="button" onclick="tiaozhuan();" value="确定"/>
<br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;使用说明：如服务器部署的IP地址是<%=ConstUtil.IP %><br/>
&nbsp;&nbsp;&nbsp;&nbsp;接口名称是接口的显示简称，<br/>
&nbsp;&nbsp;&nbsp;&nbsp;接口描述是接口的详细描述信息，<br/>
&nbsp;&nbsp;&nbsp;&nbsp;当访问路径参数是(ss)项目名称是(aa)时可以通过<%=ConstUtil.JSON_TEST_PATH %>/ss/aa<br/>
&nbsp;&nbsp;&nbsp;&nbsp;<%=ConstUtil.JSON_TEST_PATH %>(为固定格式)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;若返回值为0则表示错误<br/>
</body>
</html>