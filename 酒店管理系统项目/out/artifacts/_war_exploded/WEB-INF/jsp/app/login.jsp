<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <!-- Custom Theme files -->
    <link href="../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="../css/app/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- //Custom Theme files -->
    <!-- js -->
    <script src="../js/jquery-2.2.3.min.js">  </script>
    <script src="../js/bootstrap.js"></script>
    <script type="text/javascript">
        //在页面完成加载时调用
        window.onload = function(){
            document.getElementById("loginname").focus();
            document.onkeydown = function(){
                // firefox没有window.event对象
                var event = arguments[0] ? arguments[0] : window.event;
                if (event.keyCode === 13){
                    submitTable();
                }
            };
        };

        function submitTable() {
            var loginname = document.getElementById("loginname").value;
            var password = document.getElementById("password").value;

            if (loginname == null  ||  loginname=="") {
                $("#message").val("请输入用户名");
                document.getElementById("loginname").focus();
                return false;
            }
            if (password == null  ||  password=="") {
                $("#message").val("请输入密码");
                document.getElementById("password").focus();
                return  false;
            }

            document.getElementById("loginform").submit();

        }


    </script>
</head>
<body>
<!-- banner -->
<div class="banner about-w3bnr">
    <!-- header -->
    <div class="header">
        <!-- //header-one -->
        <!-- navigation -->
        <div class="navigation agiletop-nav">
            <div class="container">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header w3l_logo">
                        <h1><a href="app/index.do">Ascott</a></h1>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/app/index.do" class="active">主页</a></li>
                            <!-- Mega Menu -->
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/app/menuList.do" class="dropdown-toggle" ">菜单 </a>
                            </li>
                            <li class="w3pages">
                                <a href="${pageContext.request.contextPath}/app/room.do">客房</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- //navigation -->
    </div>
    <!-- //header-end -->
</div>
<!-- //banner -->
<!-- breadcrumb -->
<div class="container">
    <ol class="breadcrumb w3l-crumbs">
        <li><a href="${pageContext.request.contextPath}/app/index.do"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">登录</li>
    </ol>
</div>
<!-- //breadcrumb -->
<!-- login-page -->
<div class="login-page about">
    <div class="container">
        <h3 class="w3ls-title w3ls-title1">登录您的账户</h3>
        <div class="login-agileinfo">
            <form action="${pageContext.request.contextPath}/app/login.do" method="post" id="loginform" onsubmit="return submitTable()">
                <input type="hidden"  name="method" value="submitTable">
                <center> <font color="red" id="message"></font></center>
                <input class="agile-ltext" type="text" id="loginname" name="loginname" placeholder="请输入用户名" value="" required="">
                <input class="agile-ltext" type="password" id="password" name="password" placeholder="请输入密码"  required="">
                <div class="wthreelogin-text">
                    <ul>
                        <li>
                            <label class="checkbox">
                                <input type="checkbox" id="remenber" name="remenber" value="reme"><i></i>
                                记住我 ?
                            </label>
                        </li>
                    </ul>
                    <div class="clearfix"> </div>
                </div>
                <input type="submit" onclick="submitTable()"  value="登录">
            </form>
            <p>没有帐号? <a href="${pageContext.request.contextPath}/app/register.do"> 现在注册!</a></p>
        </div>
    </div>
</div>
</body>
</html>