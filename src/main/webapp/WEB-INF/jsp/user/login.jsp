<%@page import="com.itlijunjie.openci.util.ConstUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>后台管理登录界面</title>
    <link href="<%=ConstUtil.SERVER_RESOURCES %>/css/alogin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=ConstUtil.SERVER_RESOURCES %>/jQuery3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        function checkcode(img) {
            img.src = "drawCheckcode?" + Math.random();
        }

        function submitForm() {
            if ($("#username").value == "") {
                alert("请输入管理员帐号");
                $("#username").focus();
                return false;
            }
            if ($("#password").value == "") {
                alert("请输入管理员密码");
                $("#password").focus();
                return false;
            }
            if ($("#checkCode").value == "") {
                alert("请输入验证码");
                $("#checkCode").focus();
                return false;
            }
            document.getElementById('login').submit();
        }

        function doKeypress(input) {
            if (event.keyCode == 13) {
                var inputId = input.id;
                if (inputId == 'checkCode') {
                    submitForm();
                } else if (inputId == 'username') {
                    $("#password").focus();
                } else if (inputId == 'password') {
                    $("#checkCode").focus();
                }
            }
        }
    </script>
</head>
<body>
<form id="login" method="post">
    <div class="Main">
        <ul>
            <li class="top"></li>
            <li class="top2"></li>
            <li class="topA"></li>
            <li class="topB">
                <span>
                    <img src="<%=ConstUtil.SERVER_RESOURCES %>/images/login/logo.gif" alt="" style=""/>
                </span>
            </li>
            <li class="topC"></li>
            <li class="topD">
                <ul class="login">
                    <li>
                        <span class="left">用户名：</span>
                        <span style="">
                            <input id="username" type="text" class="txt" name="username" onkeypress="doKeypress(this)"/>
                        </span>
                    </li>
                    <li>
                        <span class="left">密 码：</span>
                        <span style="">
                            <input id="password" type="password" class="txt" name="password" onkeypress="doKeypress(this)"/>
                        </span>
                    </li>
                    <li>
                        <span class="left">验证码：</span>
                        <span style="">
                            <input id="checkCode" type="text" class="txtCode" name="checkCode" onkeypress="doKeypress(this)"/>
                        </span>
                        <img alt="验证码" src="drawCheckcode" onclick="checkcode(this)" style="cursor: pointer;vertical-align:middle;"/>
                        ${error}
                    </li>
                    <!-- <li>
                        <span class="left">记住我：</span><input id="Checkbox1" type="checkbox"/>
                    </li> -->
                </ul>
            </li>
            <li class="topE"></li>
            <li class="middle_A"></li>
            <li class="middle_B"></li>
            <li class="middle_C">
                <span class="btn">
                    <a style="cursor: pointer;" onclick="submitForm();">
                        <img src="<%=ConstUtil.SERVER_RESOURCES %>/images/login/btnlogin.gif"/>
                    </a>
                </span>
            </li>
            <li class="middle_D"></li>
            <li class="bottom_A"></li>
            <li class="bottom_B">Open CI</li>
        </ul>
    </div>
</form>
</body>
</html>
