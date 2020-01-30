<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <!-- Custom Theme files -->
    <link href="../css/app/bootstrap.css" type="text/css" rel="stylesheet" media="all">
    <link href="../css/app/style.css" type="text/css" rel="stylesheet" media="all">
    <!-- //Custom Theme files -->
    <!-- js -->
    <script src="../js/jquery-2.2.3.min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <!-- regex.js是正则表达式的一系列判断 -->
    <script type=text/javascript src="${pageContext.request.contextPath}/js/regex.js"></script>
    <script type="text/javascript">
        $(function(){
            //不同意协议  注册提交按钮为灰无法使用
            var regBtn = jQuery("#register");
            jQuery("#readme").change(function(){
                var checkedValue = jQuery("#readme").prop("checked");
                if(checkedValue){
                    regBtn.prop("disabled",false);
                    regBtn.css("background","#08cae2");
                }else{
                    regBtn.prop("disabled",true);
                    regBtn.css("background","#909090");
                }
            });
        });

        function formOnblur(id) {
            var fieldValue = document.getElementById(id).value;
            document.getElementById(id + "_error").innerHTML = "";
            var flag = true;
            //此处有bug需要修改
            if (id == "loginName") {
                if (fieldValue == null  ||  fieldValue == "")  {
                    document.getElementById(id + "_error").innerHTML = "请输入正确登录名";
                    flag = false;
                }
                else {
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/app/register.do",
                        data: "method=ajaxLoginName&loginName=" + fieldValue,
                        async: false,
                        success: function (msg) {
                            if (msg) {
                                document.getElementById(id+"_error").innerHTML = msg;
                                flag = false;
                            }
                        }
                    });
                }
            } else if (id == "phone") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入正确电话号码";
                    flag = false;
                } else {
                    if (!checkPhone(fieldValue)) {
                        document.getElementById(id + "_error").innerHTML = "输入的电话号码不合法";
                        flag = false;
                    }
                }
            } else if (id == "email") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入正确邮箱";
                    flag = false;
                } else {
                    if (!checkEmail(fieldValue)) {
                        document.getElementById(id + "_error").innerHTML = "输入的邮箱不合法";
                        flag = false;
                    }
                }
            } else if (id == "passWord") {
                if (fieldValue == null || fieldValue == "") {
                    document.getElementById(id + "_error").innerHTML = "请输入6-16位英文或数字";
                    flag = false;
                } else {
                    if (!checkPassword(fieldValue)) {
                        document.getElementById(id + "_error").innerHTML = "输入的密码不合法";
                        flag = false;
                    }
                }
            } else if (id == "okPassWord") {
                var pass = document.getElementById("passWord").value;
                if (fieldValue == null || fieldValue == "" || fieldValue != pass) {
                    document.getElementById(id + "_error").innerHTML = "两次输入的密码不一致或密码为空";
                    flag = false;
                }
            }
            return flag;
        }

        function onRegister() {
            var ids = ["loginName","phone","email","passWord","okPassWord"];
            for (var i = 0 ; i < ids.length ; i++) {
                if (!formOnblur(ids[i])) {
                    return false;
                }
            }
            document.getElementById("registerform").submit();
        }

    </script>
</head>
<body>
<!-- banner -->
<div class="banner about-w3bnr">
    <!-- header -->
    <div class="header">
        <!-- navigation -->
        <div class="navigation agiletop-nav">
            <div class="container">
                <nav class="navbar navbar-default">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header w3l_logo">
                        <h1><a href="${pageContext.request.contextPath}/app/index.do">Ascott</a></h1>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${pageContext.request.contextPath}/app/index.do" class="active">主页</a></li>
                            <!-- Mega Menu -->
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/app/menuList.do" class="dropdown-toggle" data-toggle="dropdown">菜单 </a>
                            </li>
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/app/room.do" class="dropdown-toggle" data-toggle="dropdown">客房 </a>
                            </li>
                            <li class="w3pages">
                                <a href="${pageContext.request.contextPath}/app/order.action?method=list" >我的订单</a>
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
        <li><a href="${pageContext.request.contextPath}/app/index.action"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">注册</li>
    </ol>
</div>
<!-- //breadcrumb -->
<!-- sign up-page -->
<div class="login-page about">
    <div class="container">
        <h3 class="w3ls-title w3ls-title1">Welcome</h3>
        <div class="login-agileinfo">
            <form action="${pageContext.request.contextPath}/app/register.do" method="post" id="registerform" onsubmit="return onRegister()">
                <center> <font color="red">${message}</font></center>
                <input type="hidden" name="method"  value="submitTable">

                <input class="agile-ltext" type="text" id="loginName" name="loginName" placeholder="请输入登录名"
                       onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" required="" >
                <label id="loginName_error" class="null"></label>

                <input class="agile-ltext" type="text" id="phone" name="phone" placeholder="请输入手机号码"
                       onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" required="">
                <label id="phone_error" class="null"></label>

                <input class="agile-ltext" type="email" id="email" name="email" placeholder="请输入邮箱"
                       onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" required="">
                <label id="email_error" class="null"></label>

                <input class="agile-ltext" type="password"  id="passWord" name="passWord" placeholder="请输入密码"
                       onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" required="">
                <label id="passWord_error" class="null"></label>

                <input class="agile-ltext" type="password"  id="okPassWord" name="okPassWord" placeholder="请再次输入密码"
                       onfocus="formOnfocus(this.id);" onblur="formOnblur(this.id);" required="">
                <label id="okPassWord_error" class="null"></label>

                <div class="wthreelogin-text">
                    <ul>
                        <li>
                            <label class="checkbox">
                                <input type="checkbox" name="readme" id="readme"  checked="checked"><i></i>
                                <span>我已认真阅读并同意《Ascott》</span>
                            </label>
                        </li>
                    </ul>
                    <!-- <div class="clearfix"> </div> -->
                </div>
                <input type="submit" id="register" onclick="onRegister()" value="注册">
            </form>
            <p>已有帐号?  <a href="${pageContext.request.contextPath}/app/login.do"> 现在登录!</a></p>
        </div>
    </div>
</div>
<div class="copyw3-agile">
    <div class="container">
        <p>Ascott</p>
    </div>
</div>
</body>
</html>