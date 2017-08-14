<%@page import="com.itlijunjie.openci.util.ConstUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户</title>
    <script type="text/javascript"
            src="<%=ConstUtil.SERVER_RESOURCES%>/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="application/javascript">
        function btnAction() {
            $.post('build' ,function (data) {
                alert(data)
            });
        }
    </script>
</head>
<body>
<button onclick="btnAction()">biuld QQHeader</button>
</body>
</html>